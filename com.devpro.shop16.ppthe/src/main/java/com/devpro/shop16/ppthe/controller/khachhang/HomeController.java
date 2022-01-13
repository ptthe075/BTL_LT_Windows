package com.devpro.shop16.ppthe.controller.khachhang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shop16.ppthe.controller.BaseController;
import com.devpro.shop16.ppthe.entities.Contact;
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
		model.addAttribute("products", productService.findAll());

		return "khachhang/home/index";
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

}
