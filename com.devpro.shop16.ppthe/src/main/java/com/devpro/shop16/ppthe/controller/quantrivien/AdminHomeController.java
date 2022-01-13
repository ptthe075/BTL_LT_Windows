package com.devpro.shop16.ppthe.controller.quantrivien;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shop16.ppthe.controller.BaseController;
import com.devpro.shop16.ppthe.dto.CartItem;
import com.devpro.shop16.ppthe.entities.SaleOrder;
import com.devpro.shop16.ppthe.entities.User;
import com.devpro.shop16.ppthe.services.SaleOrderService;
import com.devpro.shop16.ppthe.services.UserService;

@Controller
public class AdminHomeController extends BaseController {
	@Autowired
	UserService userService;
	
	@Autowired
	SaleOrderService saleOrderService;
	
	@RequestMapping(value = { "/admin",}, method = RequestMethod.GET)
	public String home(final Model modal, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		modal.addAttribute("countProduct", productService.findAll().size());
		modal.addAttribute("countOrder", saleOrderService.findAll().size());
		modal.addAttribute("countUser", userService.findAll().size());
		
		List<SaleOrder> saleOrders = saleOrderService.findAll();
		BigDecimal total = BigDecimal.ZERO;

		for (SaleOrder so : saleOrders) {
			total = total.add(so.getTotal());
		}
		
		modal.addAttribute("totalOrder", total);
		
		return "quantrivien/home/index";
	}
	
	@RequestMapping(value = { "/admin/my-account",}, method = RequestMethod.GET)
	public String myAccount(final Model modal, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		User user = userService.getById(getUserLogined().getId());
		modal.addAttribute("user", user);
		
		return "quantrivien/home/my_account";
	}
	
	@RequestMapping(value = { "/admin/my-account",}, method = RequestMethod.POST)
	public String updateMyAccount(final Model modal, final HttpServletRequest request, final HttpServletResponse response,  @ModelAttribute("user") User user)
			throws Exception {
		User userInDB = userService.getById(user.getId());
		userInDB.setName(user.getName());
		userInDB.setEmail(user.getEmail());
		userInDB.setPhone(user.getPhone());
		userService.saveOrUpdate(userInDB);
		
		modal.addAttribute("TB", "TB");
		
		return "quantrivien/home/my_account";
	}
	

	@RequestMapping(value = { "/ajax/account/check-pass"}, method = RequestMethod.POST)
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
	
	@RequestMapping(value = { "/ajax/account/change-pass"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> ajax_ChangePass(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody User user) {
		
		User u = userService.getById(user.getId());
		
		u.setPassword(new BCryptPasswordEncoder(4).encode(user.getPassword()));
		
		userService.saveOrUpdate(u);
		
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);

		return ResponseEntity.ok(jsonResult);
	}
}
