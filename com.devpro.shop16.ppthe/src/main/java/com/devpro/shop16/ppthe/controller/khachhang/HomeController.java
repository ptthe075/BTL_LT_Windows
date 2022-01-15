package com.devpro.shop16.ppthe.controller.khachhang;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shop16.ppthe.controller.BaseController;
import com.devpro.shop16.ppthe.entities.Category;
import com.devpro.shop16.ppthe.entities.Contact;
import com.devpro.shop16.ppthe.entities.Information;
import com.devpro.shop16.ppthe.entities.Product;
import com.devpro.shop16.ppthe.entities.Slider;
import com.devpro.shop16.ppthe.entities.Subscribe;
import com.devpro.shop16.ppthe.services.ContactService;
import com.devpro.shop16.ppthe.services.ProductService;
import com.devpro.shop16.ppthe.services.SubscribeService;

@Controller
public class HomeController extends BaseController {

	@Autowired
	private ProductService productService;
	@Autowired
	private SubscribeService subscribeService;
	@Autowired
	private ContactService contactService;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String home(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		
		String sql = "SELECT * FROM tbl_silder WHERE is_Hot = 1 AND status = 1";
		List<Slider> sliders =  sliderService.executeByNativeSQL(sql, 0).getData();
		model.addAttribute("slidesHome",sliders);
		
		sql = "SELECT * FROM tbl_product WHERE is_Hot = 1 AND status = 1";
		List<Product> products = productService.executeByNativeSQL(sql, 0).getData();
		
		List<Category> categories = categoryParents();
		String hotProducts = buildHotProducts(products, categories);
		model.addAttribute("hotProducts", hotProducts);
		
		return "khachhang/home/index";
	}
	
	@RequestMapping(value = { "/about/{informationSeo}" }, method = RequestMethod.GET)
	public String about(final Model model, final HttpServletRequest request, final HttpServletResponse response, @PathVariable("informationSeo") String informationSeo)
			throws Exception {
		
		String sql = "SELECT * FROM tbl_information WHERE seo = '" + informationSeo + "' AND status = 1";
		Information information  =  informationService.executeByNativeSQL(sql, 0).getData().get(0);
		model.addAttribute("information",information);
		
		return "khachhang/home/about";
	}

	@RequestMapping(value = { "/ajax/subcrible" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> ajax_Subscribe(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody Subscribe subscribe) {

		String sql = "SELECT * FROM tbl_subscribe WHERE email = '" + subscribe.getEmail().trim() + "'";
		Subscribe sub = subscribeService.getOneByNativeSQL(sql);

		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("message", "Cảm ơn đã đăng ký. Bạn sẽ nhận được các chương trình khuyến mãi trong email: "
				+ subscribe.getEmail() + "!");

		if (sub == null) {
			subscribeService.saveOrUpdate(subscribe);
		} else {
			jsonResult.put("err", "Email này đã được lưu");
		}

		return ResponseEntity.ok(jsonResult);
	}

	@RequestMapping(value = { "/contact" }, method = RequestMethod.GET)
	public String contact(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		Contact contact = new Contact();

		model.addAttribute("contact", contact);

		return "khachhang/home/contact";
	}

	@RequestMapping(value = { "/contact" }, method = RequestMethod.POST)
	public String contact(final Model model, final HttpServletRequest request, final HttpServletResponse response,
			final @ModelAttribute("contact") Contact contact) throws Exception {

		contactService.saveOrUpdate(contact);
		model.addAttribute("TB", "Đã nhận được thông tin của bạn: " + contact.getFullName() + "!");
		model.addAttribute("contact", new Contact());

		return "khachhang/home/contact";
	}
	
	private String buildHotProducts(List<Product> products, List<Category> categories) {
		StringBuilder hotProducts = new StringBuilder();
		
		for (Category c : categories) {
			StringBuilder cate = new StringBuilder();
			for (Product p : products) {
				if(p.hasParentCategory(c)) {
					cate.append(buildProduct(p,c));
				}
			}
			if(cate.length() != 0) {
				hotProducts.append("<div class=\"col c-12 block-container\"> <div class=\"border-radius box-shadow\"> <div class=\"col c-4 box-title\"> <a href=\"/category/" + c.getSeo() + "\" class=\"box-title__title\"> <h2>"+c.getName()+" nổi bật</h2> </a> </div> <div class=\"col c-12 box-list__wrapper\"> <div class=\"btn-action__prev\" name-category=\""+c.getSeo()+"\"> <i class=\"fas fa-chevron-left\"></i> </div> <div class=\"btn-action__next\" name-category=\""+c.getSeo()+"\"> <i class=\"fas fa-chevron-right\"></i> </div> <div class=\"row\"> <div class=\"col c-12 list-product\" name-category=\""+c.getSeo()+"\">");
				hotProducts.append(cate);
				hotProducts.append("</div></div></div></div></div>");
			}
		}
		
		return hotProducts.toString();
	}

	private String buildProduct(Product p, Category c) {
		StringBuilder product = new StringBuilder();
		
		product.append("<div class=\"item-product border-radius border-1 margin-12-6\">");
		
		if(p.getPriceSale() != null) {
			product.append("<div class=\"item-product__percent\">");
			int value = (int)Math.floor(p.getPriceSale().doubleValue() / p.getPrice().doubleValue() * 100);
			int discount = 100 - value;
			product.append("<p>giảm "+discount+"%</p>");
			product.append("</div>");
		}
		
		product.append("<div class=\"item-product__img\"> <a href=\"/product/detail/"+p.getSeo()+"\"> <img src=\"/Upload/Products/"+p.getAvatar()+"\" width=\"100%\" alt=\"\"> </a> </div>");
		product.append("<div class=\"item-product__name\"> <a href=\"/product/detail/"+p.getSeo()+"\">"+p.getName()+"</a> </div>");
		
		DecimalFormat df = new DecimalFormat("#,### ₫");
		
		product.append("<div class=\"item-product__price\">");
		if(p.getPriceSale() != null) {
			product.append("<div class=\"special-price\">"+df.format(p.getPriceSale())+"</div> <div class=\"old-price\">"+df.format(p.getPrice())+"</div>");
		}else {
			product.append("<div class=\"special-price\">"+df.format(p.getPrice())+"</div>");
		}
		product.append("</div>");
		
		product.append("<div class=\"item-product__action\"> <div class=\"item-product__rating\"> </div> <a onclick=\"javascript:AddToCart('', "+p.getId()+", 1);\" class=\"item-product__cart\"> <i class=\"fas fa-cart-plus\"></i> </a> </div>");
		product.append("</div>");
		return product.toString();
	}

}
