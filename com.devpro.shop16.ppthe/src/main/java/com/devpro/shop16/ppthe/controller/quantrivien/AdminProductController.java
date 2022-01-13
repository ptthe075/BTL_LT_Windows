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
import com.devpro.shop16.ppthe.entities.Product;
import com.devpro.shop16.ppthe.entities.ProductImage;
@Controller
@RequestMapping("/admin/products")
public class AdminProductController extends BaseController {
	

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String index(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		model.addAttribute("products", productService.findAll());

		return "quantrivien/products/list";
	}

	@RequestMapping(value = { "/management" }, method = RequestMethod.GET)
	public String adminProductAdd(final Model model, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		
		model.addAttribute("product", new Product()); 

		return "quantrivien/products/product_management";
	}

	@RequestMapping(value = { "/management/{productId}" }, method = RequestMethod.GET)
	public String adminProductEdit(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @PathVariable("productId") int productId) throws Exception {

		// lấy sản phẩm trong db
		Product product = productService.getById(productId);

		// edit sản phẩm
		model.addAttribute("product", product);

		return "quantrivien/products/product_management";
	}

	@RequestMapping(value = { "/management" }, method = RequestMethod.POST)
	public String adminProductAddOrUpdate(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @ModelAttribute("product") Product product, // spring-form biding với
																							// product model
			@RequestParam("add_img-avatar") MultipartFile productAvatar, // hứng file đẩy lên
			@RequestParam("add_img-pictures") MultipartFile[] productPictures // hứng file đẩy lên)
	) throws Exception {

		// cần kiểm tra xem id của product
		// = null || = 0 thì thêm mới
		// ngược lại là chỉnh sửa
		if (product.getId() == null || product.getId() <= 0) {
			productService.saveOrUpdate(product);
			productService.add(product, productAvatar, productPictures);
		} else {
			productService.update(product, productAvatar, productPictures);
		}

		// MVC được cấu hình chỉ định vào thư mục /src/main/webapp/WEB-INF/views
		// để tìm các views
		// /WEB-INF/views/user/index.jsp

		return "redirect:/admin/products";
	}

	@RequestMapping(value = { "/ajax/delete_image" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteImage(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody ProductImage productImage) {
		// productImage.getId() - parent_id
		// productImage.getPath() - path
		productService.deleteImage(productImage.getId(), productImage.getPath());

		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);

		return ResponseEntity.ok(jsonResult);
	}
	
	@RequestMapping(value = { "/management_ajax" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteOrRestoreProduct(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody Product product) {
		
		Product productOld = productService.getById(product.getId());
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		
		if(productOld.getStatus()) {
			productOld.setStatus(false);
			jsonResult.put("status", false);
		}else {
			productOld.setStatus(true);
			jsonResult.put("status", true);
		}
		
		productService.saveOrUpdate(productOld);

		return ResponseEntity.ok(jsonResult);
	}
}
