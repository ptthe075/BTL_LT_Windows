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
import com.devpro.shop16.ppthe.entities.Category;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController extends BaseController {

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String index(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		return "quantrivien/categories/list";
	}

	@RequestMapping(value = { "/management", }, method = RequestMethod.GET)
	public String adminCategoryAdd(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		model.addAttribute("category", new Category());

		return "quantrivien/categories/category_management";
	}

	@RequestMapping(value = { "/management/{categoryId}", }, method = RequestMethod.GET)
	public String adminCategoryEdit(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @PathVariable("categoryId") int categoryId) throws Exception {
		
		Category category = categoryService.getById(categoryId);

		model.addAttribute("category", category);

		return "quantrivien/categories/category_management";
	}

	@RequestMapping(value = { "/management", }, method = RequestMethod.POST)
	public String adminCategoryAddOrUpdate(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @ModelAttribute("category") Category category,
			@RequestParam("add_img-avatar") MultipartFile categoryAvatar) throws Exception {

		if (category.getId() == null || category.getId() <= 0) {
			if(category.getParent().getId() == null) {
				category.setParent(null);
			}
			categoryService.saveOrUpdate(category);
			categoryService.add(category, categoryAvatar);
		} else {
			categoryService.update(category, categoryAvatar);
		}

		return "redirect:/admin/categories";
	}
	
	@RequestMapping(value = { "/management_ajax" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteOrRestoreCategory(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody Category category) {
		
		Category categoryOld = categoryService.getById(category.getId());
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		
		if(categoryOld.getStatus()) {
			categoryOld.setStatus(false);
			jsonResult.put("status", false);
		}else {
			categoryOld.setStatus(true);
			jsonResult.put("status", true);
		}
		
		categoryService.saveOrUpdate(categoryOld);

		return ResponseEntity.ok(jsonResult);
	}
}
