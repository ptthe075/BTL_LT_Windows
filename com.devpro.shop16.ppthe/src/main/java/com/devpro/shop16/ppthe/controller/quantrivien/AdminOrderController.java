package com.devpro.shop16.ppthe.controller.quantrivien;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shop16.ppthe.controller.BaseController;
import com.devpro.shop16.ppthe.services.SaleOrderService;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController extends BaseController {
	@Autowired
	SaleOrderService saleOrderService;

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String index(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		
		model.addAttribute("orders", saleOrderService.findAll());
		
		return "quantrivien/orders/list";
	}
}
