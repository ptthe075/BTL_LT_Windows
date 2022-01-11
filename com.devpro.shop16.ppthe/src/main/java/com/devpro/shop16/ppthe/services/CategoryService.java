package com.devpro.shop16.ppthe.services;

import java.io.File;
import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.devpro.shop16.ppthe.entities.Category;
import com.github.slugify.Slugify;

@Service
public class CategoryService extends BaseService<Category> {

	@Override
	protected Class<Category> clazz() {
		return Category.class;
	}

	private boolean isEmptyUploadFile(MultipartFile image) {
		return image == null || image.getOriginalFilename().isEmpty();
	}

	@Transactional
	public Category add(Category c, MultipartFile categoryAvatar) throws IllegalStateException, IOException {
		String path = UPLOAD_FOLDER_ROOT + "Categories/";

		if (!isEmptyUploadFile(categoryAvatar)) {
			String pathSub = c.getId() + "";
			if (new File(path + pathSub).mkdirs()) {
				categoryAvatar.transferTo(new File(path + pathSub + "/" + categoryAvatar.getOriginalFilename()));

				c.setImage(pathSub + "/" + categoryAvatar.getOriginalFilename());
			}
		}

		c.setSeo(new Slugify().slugify(c.getName()));

		return super.saveOrUpdate(c);
	}

	@Transactional
	public Category update(Category c, MultipartFile categoryAvatar) throws IllegalStateException, IOException {
		String path = UPLOAD_FOLDER_ROOT + "Categories/";
		Category categoryInDb = super.getById(c.getId());

		if (!isEmptyUploadFile(categoryAvatar)) {
			String pathSub = c.getId() + "/";
			new File(path + categoryInDb.getImage()).delete();

			categoryAvatar.transferTo(new File(path + pathSub + categoryAvatar.getOriginalFilename()));
			c.setImage(pathSub + categoryAvatar.getOriginalFilename());
		} else {
			c.setImage(categoryInDb.getImage());
		}

		c.setSeo(new Slugify().slugify(c.getName()));

		return super.saveOrUpdate(c);
	}

}
