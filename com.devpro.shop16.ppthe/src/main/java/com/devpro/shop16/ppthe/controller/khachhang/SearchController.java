package com.devpro.shop16.ppthe.controller.khachhang;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shop16.ppthe.controller.BaseController;
import com.devpro.shop16.ppthe.dto.ProductSearchModel;
import com.devpro.shop16.ppthe.entities.Brand;
import com.devpro.shop16.ppthe.entities.Category;
import com.devpro.shop16.ppthe.entities.Product;

@Controller
public class SearchController extends BaseController {
	
	@RequestMapping(value = { "/brand/{brandSeo}", }, method = RequestMethod.GET)
	public String brandSearch(final Model model, final HttpServletRequest request, final HttpServletResponse response, @PathVariable("brandSeo") String brandSeo) throws Exception {
		ProductSearchModel searchModel = new ProductSearchModel();
		
		String sql = "SELECT * FROM tbl_brand WHERE seo = '"+brandSeo+"'";
		Brand brand = brandService.executeByNativeSQL(sql, 0).getData().get(0);
		
		model.addAttribute("title", "Thương hiệu " + brand.getName().toLowerCase());
		
		model.addAttribute("breadcrumb", buildBreadcrumb("<li class=\"breadcrumb-item\">"+brand.getName()+"</li>"));
		
		searchModel.brandId = brand.getId();
		List<Product> products= productService.search(searchModel).getData();
		
		model.addAttribute("listProduct", products);
		
		return "khachhang/products/list";
	}
	
	@RequestMapping(value = { "/category/{categorySeo}", }, method = RequestMethod.GET)
	public String categorySearch(final Model model, final HttpServletRequest request, final HttpServletResponse response, @PathVariable("categorySeo") String categorySeo) throws Exception {
		ProductSearchModel searchModel = new ProductSearchModel();
		
		String sql = "SELECT * FROM tbl_category WHERE seo = '"+categorySeo+"'";
		Category category = categoryService.executeByNativeSQL(sql, 0).getData().get(0);
		
		model.addAttribute("title", "Danh mục "+category.getName().toLowerCase());
		
		model.addAttribute("breadcrumb", buildBreadcrumb(category, null));
		
		searchModel.categoryId = category.getId();
		List<Product> products= productService.search(searchModel).getData();
		
		model.addAttribute("listProduct", products);
		
		return "khachhang/products/list";
	}
	
	@RequestMapping(value = { "/search/{categorySeo}/{brandSeo}", }, method = RequestMethod.GET)
	public String categorySearch(final Model model, final HttpServletRequest request, final HttpServletResponse response, @PathVariable("categorySeo") String categorySeo, @PathVariable("brandSeo") String brandSeo) throws Exception {
		ProductSearchModel searchModel = new ProductSearchModel();
		
		String sql = "SELECT * FROM tbl_category WHERE seo = '"+categorySeo+"'";
		Category category = categoryService.executeByNativeSQL(sql, 0).getData().get(0);

		sql = "SELECT * FROM tbl_brand WHERE seo = '"+brandSeo+"'";
		Brand brand = brandService.executeByNativeSQL(sql, 0).getData().get(0);
		
		model.addAttribute("title", "Tìm kiếm");
		
		
		model.addAttribute("breadcrumb", buildBreadcrumb(category, brand));
		
		searchModel.categoryId = category.getId();
		searchModel.brandId = brand.getId();
		List<Product> products= productService.search(searchModel).getData();
		
		model.addAttribute("listProduct", products);
		
		return "khachhang/products/list";
	}

	private String buildBreadcrumb(String str) {
		StringBuilder breadcrumb = new StringBuilder("<div class=\"block-breadcrumbs box-shadow\"> <div class=\"grid breadcrumbs\"> <ul class=\"breadcrumb-list\"> <li class=\"breadcrumb-item\"> <a href=\"/\" class=\"breadcrumb-link\"> <i class=\"fas fa-home breadcrumb-home\"></i> Trang chủ </a> <i class=\"fas fa-angle-right\"></i> </li>");
		if(str != null && str.length() > 0) {
			breadcrumb.append(str);
		}
		breadcrumb.append("</ul></div></div>");
		return breadcrumb.toString();
	}
	
	private String buildBreadcrumb(Category category, Brand b) {
		StringBuilder breadcrumb = new StringBuilder("<div class=\"block-breadcrumbs box-shadow\"> <div class=\"grid breadcrumbs\"> <ul class=\"breadcrumb-list\"> <li class=\"breadcrumb-item\"> <a href=\"/\" class=\"breadcrumb-link\"> <i class=\"fas fa-home breadcrumb-home\"></i> Trang chủ </a> <i class=\"fas fa-angle-right\"></i> </li>");
		
		List<Category> categories = category.listParentCategories(new LinkedList<Category>());
		
		if(b != null) {
			for (Category c : categories) {
				breadcrumb.append("<li class=\"breadcrumb-item\"> <a href=\"/category/" + c.getSeo() + "\" class=\"breadcrumb-link\"> " + c.getName() + " </a> <i class=\"fas fa-angle-right\"></i> </li>");
			}
			
			breadcrumb.append("<li class=\"breadcrumb-item\">"+b.getName()+"</li>");
		} else {
			for (int i = 0; i < categories.size() - 1; i++) {
				Category c = categories.get(i);
				breadcrumb.append("<li class=\"breadcrumb-item\"> <a href=\"/category/" + c.getSeo() + "\" class=\"breadcrumb-link\"> " + c.getName() + " </a> <i class=\"fas fa-angle-right\"></i> </li>");
			}
			
			breadcrumb.append("<li class=\"breadcrumb-item\">"+categories.get(categories.size() - 1).getName()+"</li>");
		}
		
		
		breadcrumb.append("</ul></div></div>");
		return breadcrumb.toString();
	}
}
