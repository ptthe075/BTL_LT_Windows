package com.devpro.shop16.ppthe.services;

import java.io.File;
import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.devpro.shop16.ppthe.dto.ProductSearchModel;
import com.devpro.shop16.ppthe.entities.Product;
import com.devpro.shop16.ppthe.entities.ProductImage;
import com.github.slugify.Slugify;

@Service
public class ProductService extends BaseService<Product> {

	@Autowired
	ProductImageService productImagesService;

	@Override
	protected Class<Product> clazz() {
		return Product.class;
	}

	private boolean isEmptyUploadFile(MultipartFile[] images) {
		if (images == null || images.length <= 0)
			return true;

		if (images.length == 1 && images[0].getOriginalFilename().isEmpty())
			return true;

		return false;
	}

	private boolean isEmptyUploadFile(MultipartFile image) {
		return image == null || image.getOriginalFilename().isEmpty();
	}
	
	public PagerData<Product> search(ProductSearchModel searchModel) {

		// khởi tạo câu lệnh
		String sql = "SELECT * FROM tbl_product p WHERE 1=1";

		if (searchModel != null) {
			// tìm kiếm theo category
			if (searchModel.categoryId != null) {
				sql += " and category_id = " + searchModel.categoryId;
			}

			// tìm theo seo
			if (!StringUtils.isEmpty(searchModel.seo)) {
				sql += " and p.seo = '" + searchModel.seo + "'";
			}

			// tìm kiếm sản phẩm hot
			if (searchModel.isHot != null) {
				sql += " and p.is_hot = '" + searchModel.seo + "'";
			}

			// tim kiem san pham theo seachText
			if (!StringUtils.isEmpty(searchModel.keyword)) {
				sql += " and (p.name like '%" + searchModel.keyword + "%')";
			}
		}

		// chi lay san pham chua xoa
//		sql += " and p.status=1 ";
		return executeByNativeSQL(sql, searchModel == null ? 0 : searchModel.getPage());

	}

	/**
	 * Dùng để thêm mới sản phẩm
	 * 
	 * @param p
	 * @param productAvatar
	 * @param productPictures
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@Transactional
	public Product add(Product p, MultipartFile productAvatar, MultipartFile[] productPictures)
			throws IllegalStateException, IOException {

		String path = UPLOAD_FOLDER_ROOT + "Products/";

		if (!isEmptyUploadFile(productAvatar)) {
			String pathSub = p.getId() + "/Avatar";
			if (new File(path + pathSub).mkdirs()) {
				productAvatar.transferTo(new File(path + pathSub + "/" + productAvatar.getOriginalFilename()));

				p.setAvatar(pathSub + "/" + productAvatar.getOriginalFilename());
			}
		}

		if (!isEmptyUploadFile(productPictures)) {
			String pathSub = p.getId() + "/Pictures";
			if (new File(path + pathSub).mkdirs()) {
				for (MultipartFile pic : productPictures) {
					pic.transferTo(new File(path + pathSub + "/" + pic.getOriginalFilename()));

					ProductImage pi = new ProductImage();
					pi.setPath(pathSub + "/" + pic.getOriginalFilename());
					pi.setTitle(pic.getOriginalFilename());

					p.addProductImage(pi);
				}
			}
		}

		p.setSeo(new Slugify().slugify(p.getName()));

		return super.saveOrUpdate(p);
	}

	/**
	 * Cập nhật sản phẩm
	 * 
	 * @param p
	 * @param productAvatar
	 * @param productPictures
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@Transactional
	public Product update(Product p, MultipartFile productAvatar, MultipartFile[] productPictures)
			throws IllegalStateException, IOException {

		String path = UPLOAD_FOLDER_ROOT + "Products/";
		Product productInDb = super.getById(p.getId());
		
		//p.setCreatedDate(productInDb.getCreatedDate());

		if (!isEmptyUploadFile(productAvatar)) {
			String pathSub = p.getId() + "/Avatar/";
			new File(path + productInDb.getAvatar()).delete();

			productAvatar.transferTo(new File(path + pathSub + productAvatar.getOriginalFilename()));
			p.setAvatar(pathSub + productAvatar.getOriginalFilename());
		} else {
			p.setAvatar(productInDb.getAvatar());
		}

		if (!isEmptyUploadFile(productPictures)) {
			String pathSub = p.getId() + "/Pictures/";
			for (MultipartFile pic : productPictures) {
				pic.transferTo(new File(path + pathSub + pic.getOriginalFilename()));

				ProductImage pi = new ProductImage();
				pi.setPath(pathSub + pic.getOriginalFilename());
				pi.setTitle(pic.getOriginalFilename());

				p.addProductImage(pi);
			}
		}

		p.setSeo(new Slugify().slugify(p.getName()));

		return super.saveOrUpdate(p);

	}

	@Transactional
	public void deleteImage(Integer productid, String path) {
		String sql = "SELECT * FROM tbl_product_image WHERE product_id = " + productid + " AND path = '" + path + "'";
		ProductImage productImage = productImagesService.getOneByNativeSQL(sql);

		new File(UPLOAD_FOLDER_ROOT + "Products/" + productImage.getPath()).delete();
		productImagesService.delete(productImage);
	}
}
