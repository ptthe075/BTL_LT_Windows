package com.devpro.shop16.ppthe.controller.khachhang;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shop16.ppthe.controller.BaseController;
import com.devpro.shop16.ppthe.dto.ProductSearchModel;
import com.devpro.shop16.ppthe.entities.Category;
import com.devpro.shop16.ppthe.entities.Product;
import com.devpro.shop16.ppthe.services.ProductImageService;

@Controller
@RequestMapping(value = { "/product" })
public class ProductController extends BaseController {

	@Autowired
	ProductImageService productImageService;
	
	@RequestMapping(value = { "/detail/{productSeo}", }, method = RequestMethod.GET)
	public String adminProductEdit(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @PathVariable("productSeo") String productSeo) throws Exception {
		ProductSearchModel searchModel = new ProductSearchModel();
		searchModel.seo = productSeo;
		
		Product product = productService.search(searchModel).getData().get(0);
		
		String sql = "SELECT * FROM tbl_product_image WHERE product_id = "+product.getId();
		model.addAttribute("productImages", productImageService.executeByNativeSQL(sql, 0).getData());
		
		sql = "SELECT * FROM tbl_product WHERE category_id = "+product.getCategoryId()+" AND status = 1 AND id <> "+product.getId()+" LIMIT 5";
		
		model.addAttribute("breadcrumb", buildBreadcrumb(product));
		model.addAttribute("similarProducts", productService.executeByNativeSQL(sql, 0).getData());
		model.addAttribute("product", product);
		
		return "khachhang/products/detail";
	}

	private String buildBreadcrumb(Product p) {
		StringBuilder breadcrumb = new StringBuilder("<div class=\"block-breadcrumbs box-shadow\"> <div class=\"grid breadcrumbs\"> <ul class=\"breadcrumb-list\"> <li class=\"breadcrumb-item\"> <a href=\"/\" class=\"breadcrumb-link\"> <i class=\"fas fa-home breadcrumb-home\"></i> Trang chủ </a> <i class=\"fas fa-angle-right\"></i> </li>");
		
		List<Category> categories = p.getCategory().listParentCategories(new LinkedList<Category>());
		
		for (Category c : categories) {
			breadcrumb.append("<li class=\"breadcrumb-item\"> <a href=\"/category/" + c.getSeo() + "\" class=\"breadcrumb-link\"> " + c.getName() + " </a> <i class=\"fas fa-angle-right\"></i> </li>");
		}
		
		breadcrumb.append("<li class=\"breadcrumb-item\"> <a href=\"/search/" + p.getCategory().getSeo() + "/" + p.getBrand().getSeo() + "\" class=\"breadcrumb-link\"> " + p.getBrand().getName() + " </a> <i class=\"fas fa-angle-right\"></i> </li>");
		
		breadcrumb.append("<li class=\"breadcrumb-item\">"+p.getName()+"</li>");
		
		breadcrumb.append("</ul></div></div>");
		return breadcrumb.toString();
	}
}
