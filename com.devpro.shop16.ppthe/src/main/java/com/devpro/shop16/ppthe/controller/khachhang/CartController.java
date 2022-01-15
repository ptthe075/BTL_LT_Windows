package com.devpro.shop16.ppthe.controller.khachhang;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shop16.ppthe.controller.BaseController;
import com.devpro.shop16.ppthe.dto.Cart;
import com.devpro.shop16.ppthe.dto.CartItem;
import com.devpro.shop16.ppthe.entities.Product;
import com.devpro.shop16.ppthe.entities.SaleOrder;
import com.devpro.shop16.ppthe.entities.SaleOrderProduct;
import com.devpro.shop16.ppthe.entities.SaleOrderStatus;
import com.devpro.shop16.ppthe.entities.User;
import com.devpro.shop16.ppthe.services.ProductService;
import com.devpro.shop16.ppthe.services.SaleOrderService;
import com.devpro.shop16.ppthe.services.SaleOrderStatusService;
import com.devpro.shop16.ppthe.services.UserService;

@Controller
public class CartController extends BaseController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private SaleOrderService saleOrderService;
	
	@Autowired
	private SaleOrderStatusService saleOrderStatusService;

	@RequestMapping(value = { "/cart" }, method = RequestMethod.GET)
	public String home(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		
		model.addAttribute("products", productService.findAll());

		return "khachhang/cart/index";
	}

	@RequestMapping(value = { "/cart/checkout" }, method = RequestMethod.POST)
	public String cartFinish(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		SaleOrderStatus saleOrderStatus = saleOrderStatusService.getById(1);
		
		String customerPhone = request.getParameter("user_info[phone]");
		String customerAddress = request.getParameter("user_info[address]");

		SaleOrder saleOrder = new SaleOrder();
		saleOrder.setSaleOrderStatus(saleOrderStatus);

		if(super.isLogined()) {
			User user = userService.getById(getUserLogined().getId());
			saleOrder.setUserId(user.getId());
			saleOrder.setCustomerName(user.getName());
			saleOrder.setCustomerEmail(user.getEmail());
			if(user.getPhone() == null) {
				saleOrder.setCustomerPhone(customerPhone);
			}else {
				saleOrder.setCustomerPhone(user.getPhone());
			}
			saleOrder.setCustomerAddress(customerAddress);
		} else {
			String customerFullName = request.getParameter("user_info[name]");
			String customerEmail = request.getParameter("user_info[email]");
			saleOrder.setCustomerName(customerFullName);
			saleOrder.setCustomerEmail(customerEmail);
			saleOrder.setCustomerAddress(customerAddress);
			saleOrder.setCustomerPhone(customerPhone);
		}		

		saleOrder.setCode(String.valueOf(System.currentTimeMillis()));
		

		// kết các sản phẩm trong giỏ hàng cho hóa đơn
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		
		for (CartItem cartItem : cart.getCartItems()) {
			SaleOrderProduct saleOrderProduct = new SaleOrderProduct();
			saleOrderProduct.setProduct(productService.getById(cartItem.getProductId()));
			saleOrderProduct.setQuality(cartItem.getQuantity());

			// sử dụng hàm tiện ích add hoặc remove đới với các quan hệ onetomany
			saleOrder.addSaleOrderProduct(saleOrderProduct);
		}

		saleOrder.setTotal(cart.getTotalPrice());
		saleOrder.setSeo(saleOrder.getCode());
		// lưu vào cơ sở dữ liệu
		saleOrderService.saveOrUpdate(saleOrder);

		// xóa dữ liệu giỏ hàng trong session
		session.setAttribute("cart", null);
		session.setAttribute("totalItems", "0");

		return "redirect:/";
	}

	@RequestMapping(value = { "/ajax/addToCart" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> ajax_AddToCart(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody CartItem cartItem) {

		// để lấy session sử dụng thông qua request
		// session tương tự như kiểu Map và được lưu trên main memory.
		HttpSession session = request.getSession();

		// Lấy thông tin giỏ hàng.
		Cart cart = null;
		// kiểm tra xem session có tồn tại đối tượng nào tên là "cart"
		if (session.getAttribute("cart") != null) {
			cart = (Cart) session.getAttribute("cart");
		} else {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}

		// Lấy danh sách sản phẩm có trong giỏ hàng
		List<CartItem> cartItems = cart.getCartItems();

		// kiểm tra nếu có trong giỏ hàng thì tăng số lượng
		boolean isExists = false;
		for (CartItem item : cartItems) {
			if (item.getProductId() == cartItem.getProductId()) {
				isExists = true;
				item.setQuantity(item.getQuantity() + cartItem.getQuantity());
			}
		}

		// nếu sản phẩm chưa có trong giỏ hàng
		if (!isExists) {
			Product productInDb = productService.getById(cartItem.getProductId());

			cartItem.setProductName(productInDb.getName());
			cartItem.setProductAvatar(productInDb.getAvatar());
			cartItem.setPriceUnit(productInDb.getPrice());

			cart.getCartItems().add(cartItem);
		}

		// tính tổng tiền
		this.calculateTotalPrice(request);

		// trả về kết quả
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("totalItems", getTotalItems(request));

		session.setAttribute("totalItems", getTotalItems(request));
		return ResponseEntity.ok(jsonResult);
	}
	
	@RequestMapping(value = { "/ajax/updateQuanlityCart" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> ajax_UpdateQuantityCart(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody CartItem cartItem) {

		HttpSession session = request.getSession();

		Cart cart = (Cart) session.getAttribute("cart");

		List<CartItem> cartItems = cart.getCartItems();

		for (CartItem item : cartItems) {
			if (item.getProductId() == cartItem.getProductId()) {
				item.setQuantity(cartItem.getQuantity());
			}
		}

		this.calculateTotalPrice(request);

		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("totalItems", getTotalItems(request));
		jsonResult.put("totalPrice", cart.getTotalPrice());

		session.setAttribute("totalItems", getTotalItems(request));
		return ResponseEntity.ok(jsonResult);
	}
	
	@RequestMapping(value = { "/ajax/deleteItemCart" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> ajax_DelelteItemCart(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @RequestBody CartItem cartItem) {

		HttpSession session = request.getSession();

		Cart cart = (Cart) session.getAttribute("cart");

		List<CartItem> cartItems = cart.getCartItems();
		
		for (CartItem item : cartItems) {
			if (item.getProductId() == cartItem.getProductId()) {
				cartItem = item;
			}
		}
		
		cartItems.remove(cartItem);

		this.calculateTotalPrice(request);

		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("totalItems", getTotalItems(request));
		jsonResult.put("totalPrice", cart.getTotalPrice());

		session.setAttribute("totalItems", getTotalItems(request));
		return ResponseEntity.ok(jsonResult);
	}
	
	@RequestMapping(value = { "/ajax/deleteAllCart" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> ajax_DeleteAllCart(final Model model, final HttpServletRequest request,
			final HttpServletResponse response) {

		HttpSession session = request.getSession();

		Cart cart = (Cart) session.getAttribute("cart");

		List<CartItem> cartItems = cart.getCartItems();
		
		cartItems.removeAll(cartItems);

		this.calculateTotalPrice(request);

		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("totalItems", getTotalItems(request));
		jsonResult.put("totalPrice", cart.getTotalPrice());

		session.setAttribute("totalItems", getTotalItems(request));
		return ResponseEntity.ok(jsonResult);
	}	

	private int getTotalItems(final HttpServletRequest request) {
		HttpSession httpSession = request.getSession();

		if (httpSession.getAttribute("cart") == null) {
			return 0;
		}

		Cart cart = (Cart) httpSession.getAttribute("cart");
		List<CartItem> cartItems = cart.getCartItems();

		int total = 0;
		for (CartItem item : cartItems) {
			total += item.getQuantity();
		}

		return total;
	}

	private void calculateTotalPrice(final HttpServletRequest request) {

		// để lấy session sử dụng thông qua request
		// session tương tự như kiểu Map và được lưu trên main memory.
		HttpSession session = request.getSession();

		// Lấy thông tin giỏ hàng.
		Cart cart = null;
		if (session.getAttribute("cart") != null) {
			cart = (Cart) session.getAttribute("cart");
		} else {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}

		// Lấy danh sách sản phẩm có trong giỏ hàng
		List<CartItem> cartItems = cart.getCartItems();
		BigDecimal total = BigDecimal.ZERO;

		for (CartItem ci : cartItems) {
			total = total.add(ci.getPriceUnit().multiply(BigDecimal.valueOf(ci.getQuantity())));
		}

		cart.setTotalPrice(total);
	}
}
