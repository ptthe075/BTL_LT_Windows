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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.devpro.shop16.ppthe.controller.BaseController;
import com.devpro.shop16.ppthe.entities.Brand;

@Controller
@RequestMapping("/admin/brands")
public class AdminBrandController extends BaseController {

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String index(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		return "quantrivien/brands/list";
	}

	@RequestMapping(value = { "/management", }, method = RequestMethod.GET)
	public String adminBrandAdd(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		model.addAttribute("brand", new Brand());

		return "quantrivien/brands/brand_management";
	}

	@RequestMapping(value = { "/management/{brandId}", }, method = RequestMethod.GET)
	public String adminBrandEdit(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @PathVariable("brandId") int brandId) throws Exception {

		Brand brand = brandService.getById(brandId);

		model.addAttribute("brand", brand);

		return "quantrivien/brands/brand_management";
	}

	@RequestMapping(value = { "/management", }, method = RequestMethod.POST)
	public String adminBrandAddOrUpdate(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @ModelAttribute("brand") Brand brand,
			@RequestParam("add_img-avatar") MultipartFile brandAvatar) throws Exception {

		if (brand.getId() == null || brand.getId() <= 0) {
			brandService.saveOrUpdate(brand);
			brandService.add(brand, brandAvatar);
		} else {
			brandService.update(brand, brandAvatar);
		}

		return "redirect:/admin/brands";
	}
	
	@RequestMapping(value = { "/management_ajax" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteOrRestoreBrand(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody Brand brand) {
		
		Brand brandOld = brandService.getById(brand.getId());
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		
		if(brandOld.getStatus()) {
			brandOld.setStatus(false);
			jsonResult.put("status", false);
		}else {
			brandOld.setStatus(true);
			jsonResult.put("status", true);
		}
		
		brandService.saveOrUpdate(brandOld);

		return ResponseEntity.ok(jsonResult);
	}
}
