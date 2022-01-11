package com.devpro.shop16.ppthe.controller.quantrivien;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shop16.ppthe.controller.BaseController;

@Controller
public class AdminHomeController extends BaseController {
	@RequestMapping(value = { "/admin",}, method = RequestMethod.GET)
	public String home(final Model modal, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		return "quantrivien/home/index";
	}
}
