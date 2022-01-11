package com.devpro.shop16.ppthe.services;

import java.io.File;
import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.devpro.shop16.ppthe.entities.Brand;
import com.github.slugify.Slugify;

@Service
public class BrandService extends BaseService<Brand> {

	@Override
	protected Class<Brand> clazz() {
		return Brand.class;
	}

	private boolean isEmptyUploadFile(MultipartFile image) {
		return image == null || image.getOriginalFilename().isEmpty();
	}
	
	@Transactional
	public Brand add(Brand b, MultipartFile brandAvatar) throws IllegalStateException, IOException {
		String path = UPLOAD_FOLDER_ROOT + "Brands/";

		if (!isEmptyUploadFile(brandAvatar)) {
			String pathSub = b.getId() + "";
			if (new File(path + pathSub).mkdirs()) {
				brandAvatar.transferTo(new File(path + pathSub + "/" + brandAvatar.getOriginalFilename()));

				b.setImage(pathSub + "/" + brandAvatar.getOriginalFilename());
			}
		}

		b.setSeo(new Slugify().slugify(b.getName()));

		return super.saveOrUpdate(b);
	}
	
	@Transactional
	public Brand update(Brand b, MultipartFile brandAvatar) throws IllegalStateException, IOException {
		String path = UPLOAD_FOLDER_ROOT + "Brands/";
		Brand brandInDb = super.getById(b.getId());

		if (!isEmptyUploadFile(brandAvatar)){
			String pathSub = b.getId() + "/";
			new File(path + brandInDb.getImage()).delete();

			brandAvatar.transferTo(new File(path + pathSub + brandAvatar.getOriginalFilename()));
			b.setImage(pathSub + brandAvatar.getOriginalFilename());
		} else {
			b.setImage(brandInDb.getImage());
		}

		b.setSeo(new Slugify().slugify(b.getName()));

		return super.saveOrUpdate(b);
	}

}
