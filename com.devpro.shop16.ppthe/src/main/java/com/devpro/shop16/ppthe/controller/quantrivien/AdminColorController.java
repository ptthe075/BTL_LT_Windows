package com.devpro.shop16.ppthe.controller.quantrivien;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shop16.ppthe.controller.BaseController;
import com.devpro.shop16.ppthe.entities.Color;

@Controller
@RequestMapping("/admin/colors")
public class AdminColorController extends BaseController {

	@RequestMapping(value = { "/management", }, method = RequestMethod.GET)
	public String adminColorAdd(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		model.addAttribute("color", new Color());

		return "quantrivien/products/color_management";
	}

	@RequestMapping(value = { "/management/{colorId}", }, method = RequestMethod.GET)
	public String adminColorEdit(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @PathVariable("colorId") int colorId) throws Exception {

		Color color = colorService.getById(colorId);

		model.addAttribute("color", color);

		return "quantrivien/products/color_management";
	}

	@RequestMapping(value = { "/management", }, method = RequestMethod.POST)
	public String adminColorAddOrUpdate(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @ModelAttribute("color") Color color) throws Exception {
		
		
		colorService.saveOrUpdate(color);

		return "redirect:/admin/products";
	}
	
	@RequestMapping(value = { "/management_ajax" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteOrRestoreColor(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody Color color) {
		
		Color colorOld = colorService.getById(color.getId());
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		
		if(colorOld.getStatus()) {
			colorOld.setStatus(false);
			jsonResult.put("status", false);
		}else {
			colorOld.setStatus(true);
			jsonResult.put("status", true);
		}
		
		colorService.saveOrUpdate(colorOld);

		return ResponseEntity.ok(jsonResult);
	}
}
