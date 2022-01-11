package com.devpro.shop16.ppthe.controller.khachhang;

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
		
		model.addAttribute("product", product);
		
		return "khachhang/products/detail";
	}
}
