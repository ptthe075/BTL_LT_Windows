package com.devpro.shop16.ppthe.controller.quantrivien;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.devpro.shop16.ppthe.controller.BaseController;
import com.devpro.shop16.ppthe.entities.Slider;
import com.devpro.shop16.ppthe.services.SliderService;


@Controller
@RequestMapping("/admin/sliders")
public class AdminSliderController extends BaseController {
	@Autowired
	private SliderService sliderService;
	
	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String index(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		model.addAttribute("sliders", sliderService.findAll());

		return "quantrivien/sliders/list";
	}

	@RequestMapping(value = { "/management", }, method = RequestMethod.GET)
	public String adminSliderAdd(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		model.addAttribute("slider", new Slider());

		return "quantrivien/sliders/slider_management";
	}

	@RequestMapping(value = { "/management/{sliderId}", }, method = RequestMethod.GET)
	public String adminSliderEdit(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @PathVariable("sliderId") int sliderId) throws Exception {

		Slider slider = sliderService.getById(sliderId);

		model.addAttribute("slider", slider);

		return "quantrivien/sliders/slider_management";
	}

	@RequestMapping(value = { "/management", }, method = RequestMethod.POST)
	public String adminSliderAddOrUpdate(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @ModelAttribute("slider") Slider slider,
			@RequestParam("add_img-avatar") MultipartFile sliderAvatar) throws Exception {

		if (slider.getId() == null || slider.getId() <= 0) {
			sliderService.saveOrUpdate(slider);
			sliderService.add(slider,sliderAvatar);
		} else {
			sliderService.update(slider,sliderAvatar);
		}

		return "redirect:/admin/sliders";
	}
	
	@RequestMapping(value = { "/management_ajax" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteOrRestoreSlider(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody Slider slider) {
		
		Slider sliderOld = sliderService.getById(slider.getId());
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		
		if(sliderOld.getStatus()) {
			sliderOld.setStatus(false);
			jsonResult.put("status", false);
		}else {
			sliderOld.setStatus(true);
			jsonResult.put("status", true);
		}
		
		sliderService.saveOrUpdate(sliderOld);

		return ResponseEntity.ok(jsonResult);
	}
}
