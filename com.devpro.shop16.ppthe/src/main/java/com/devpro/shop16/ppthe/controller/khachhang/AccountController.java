package com.devpro.shop16.ppthe.controller.khachhang;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shop16.ppthe.controller.BaseController;
import com.devpro.shop16.ppthe.entities.SaleOrder;
import com.devpro.shop16.ppthe.entities.SaleOrderStatus;
import com.devpro.shop16.ppthe.entities.User;
import com.devpro.shop16.ppthe.services.SaleOrderService;
import com.devpro.shop16.ppthe.services.SaleOrderStatusService;
import com.devpro.shop16.ppthe.services.UserService;

@Controller
public class AccountController extends BaseController{
	@Autowired
	UserService userService;
	@Autowired
	SaleOrderService saleOrderService;
	@Autowired
	SaleOrderStatusService saleOrderStatusService;

	@RequestMapping(value = { "/account/edit-account" }, method = RequestMethod.GET)
	public String editAccount(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		if(isLogined()) {
			User user = userService.getById(getUserLogined().getId());
			model.addAttribute("myAccount", user);
			
			return "khachhang/account/my_account";
		}

		return "redirect:/";
	}
	
	@RequestMapping(value = { "/account/orders" }, method = RequestMethod.GET)
	public String myOrders(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		if(isLogined()) {
			int id = getUserLogined().getId();
			
			String sql = "SELECT * FROM tbl_saleorder WHERE user_id = "+id;
			List<SaleOrder> orders = saleOrderService.executeByNativeSQL(sql, 0).getData();
			model.addAttribute("buildOrders", buildOrders(orders));
			return "khachhang/account/my_orders";
		}

		return "redirect:/";
	}
	

	private String buildOrders(List<SaleOrder> orders) {
		StringBuilder build = new StringBuilder();
		
//		build.append("<div class=\"account-info-container\"> <h3>Đơn hàng của tôi</h3> <div class=\"account__form-container order\" style=\"overflow: hidden;\"> <ul class=\"tabs-orders\"> <li class=\"tab-order__link current\" data-tab=\"#all-menus\">Tất cả đơn</li> <li class=\"tab-order__link\" data-tab=\"#processing\">Đang xử lý</li> <li class=\"tab-order__link\" data-tab=\"#delivering\">Đang giao</li> <li class=\"tab-order__link\" data-tab=\"#delivered\">Đã giao</li> <li class=\"tab-order__link\" data-tab=\"#cancelled\">Đã hủy</li> </ul>");
		
		build.append("<div id=\"all-menus\" class=\"tab-content current\">");
		build.append(buildTabOrder(orders, 0));
		build.append("</div>");
		
		build.append("<div id=\"processing\" class=\"tab-content\">");
		build.append(buildTabOrder(orders, 1));
		build.append("</div>");
		
		build.append("<div id=\"delivering\" class=\"tab-content\">");
		build.append(buildTabOrder(orders, 2));
		build.append("</div>");
		
		build.append("<div id=\"delivered\" class=\"tab-content\">");
		build.append(buildTabOrder(orders, 3));
		build.append("</div>");
		
		build.append("<div id=\"cancelled\" class=\"tab-content\">");
		build.append(buildTabOrder(orders, 4));
		build.append("</div>");

//		build.append("</div> </div>");
		
		return build.toString();
	}

	private String buildTabOrder(List<SaleOrder> orders, int status) {
		StringBuilder build = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		DecimalFormat df = new DecimalFormat("#,### ₫");
		
		if(status == 0) {
			int stt = 0;
			for (SaleOrder so : orders) {
				stt++;
				int statusOrder = so.getSaleOrderStatus().getId();

				build.append("<tr><td>" + stt + "</td>");
				build.append("<td>" + so.getCode() + "</td>");
				build.append("<td>" + sdf.format(so.getCreatedDate())+ "</td>");
				
				if(statusOrder == 4) {
					build.append("<td style=\"color: red;\">" + so.getSaleOrderStatus().getName() + "</td>");
				} else {
					build.append("<td style=\"color: green;\">" + so.getSaleOrderStatus().getName() + "</td>");
				}
				
				build.append("<td>" + df.format(so.getTotal()) + "</td>");
				build.append("<td>" + so.getNote() + "</td>");

				
				build.append("<td><a href=\"/\" class=\"account-order__action\">Chi tiết</a>");
				
				switch (statusOrder) {
				case 1:
					build.append("<button type=\"button\" onclick=\"updateOrderStatus("+so.getId()+", 4);\" class=\"account-order__action\">Hủy đơn</button>");
					break;
				case 2:
					build.append("<button type=\"button\" onclick=\"updateOrderStatus("+so.getId()+", 3);\" class=\"account-order__action\">Đã nhận</button>");
					break;
				}
				
				build.append("</td></tr>");
			}
		} else {
			int stt = 0;
			for (SaleOrder so : orders) {
				int statusOrder = so.getSaleOrderStatus().getId();
				if(statusOrder == status) {
					stt++;
					build.append("<tr><td>" + stt + "</td>");
					build.append("<td>" + so.getCode() + "</td>");
					build.append("<td>" + sdf.format(so.getCreatedDate())+ "</td>");
					
					if(statusOrder == 4) {
						build.append("<td style=\"color: red;\">" + so.getSaleOrderStatus().getName() + "</td>");
					} else {
						build.append("<td style=\"color: green;\">" + so.getSaleOrderStatus().getName() + "</td>");
					}
					
					build.append("<td>" + df.format(so.getTotal()) + "</td>");
					build.append("<td>" + so.getNote() + "</td>");
					
					build.append("<td><a href=\"/\" class=\"account-order__action\">Chi tiết</a>");
					
					switch (statusOrder) {
					case 1:
						build.append("<button type=\"button\" onclick=\"updateOrderStatus("+so.getId()+", 4);\" class=\"account-order__action\">Hủy đơn</button>");
						break;
					case 2:
						build.append("<button type=\"button\" onclick=\"updateOrderStatus("+so.getId()+", 3);\" class=\"account-order__action\">Đã nhận</button>");
						break;
					}
					
					build.append("</td></tr>");
				}
			}
		}
		
		if(build.length() == 0) {
			return "<div class=\"no-order\"><img src=\"/assets/imgs/empty-order.png\"> <p>Chưa có đơn hàng</p> </div>";
		}else {
			return "<table class=\"table-order\"> <thead> <tr> <th style=\"width: 55px;\">STT</th> <th style=\"width: 150px;\">Mã đơn hàng</th> <th style=\"width: 175px;\">Ngày tạo</th> <th style=\"width: 140px;\">Trạng thái</th> <th style=\"width: 150px;\">Tổng tiền</th> <th>Ghi chú</th> <th style=\"width: 120px;\">Hành động</th> </tr> </thead> <tbody>"+build.toString()+"</table>";
		}
	}

	@RequestMapping(value = { "/ajax/account/check"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> ajax_CheckPass(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody User user) {
		String pass = user.getPassword();
		User u = userService.getById(user.getId());

		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		
		if(new BCryptPasswordEncoder(4).matches(pass, u.getPassword())) {
			jsonResult.put("status", true);
		}else {
			jsonResult.put("status", false);
		}

		return ResponseEntity.ok(jsonResult);
	}
	
	@RequestMapping(value = { "/ajax/account/change"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> ajax_ChangeAccount(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody User user) {
		String pass = user.getPassword();
		User u = userService.getById(user.getId());
		
		if(pass != null) {
			u.setPassword(new BCryptPasswordEncoder(4).encode(pass));
		}
		
		u.setEmail(user.getEmail());
		u.setName(user.getName());
		u.setPhone(user.getPhone());
		
		userService.saveOrUpdate(u);
		
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);

		return ResponseEntity.ok(jsonResult);
	}
	
	@RequestMapping(value = { "/ajax/account/updateOrderStatus"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> ajax_UpdateOrder(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody SaleOrder saleOrder) {
		int statusId = saleOrder.getUserId();
		SaleOrderStatus saleOrderStatus = saleOrderStatusService.getById(statusId);
		SaleOrder so = saleOrderService.getById(saleOrder.getId());
		
		so.setSaleOrderStatus(saleOrderStatus);
		so.setNote(saleOrder.getNote());
		
		saleOrderService.saveOrUpdate(so);
		
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		
		switch (statusId) {
		case 3:
			jsonResult.put("message", "Xác nhận đã giao hàng thành công");
			break;
		case 4:
			jsonResult.put("message", "Bạn đã hủy thành công đơn hàng này");
			break;
		}
		
		int id = getUserLogined().getId();
		String sql = "SELECT * FROM tbl_saleorder WHERE user_id = "+id;
		List<SaleOrder> orders = saleOrderService.executeByNativeSQL(sql, 0).getData();
		jsonResult.put("buildOrders", buildOrders(orders));

		return ResponseEntity.ok(jsonResult);
	}
}
