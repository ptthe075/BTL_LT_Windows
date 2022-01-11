package com.devpro.shop16.ppthe.services;

import java.io.File;
import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.devpro.shop16.ppthe.entities.Slider;
import com.github.slugify.Slugify;

@Service
public class SliderService extends BaseService<Slider> {

	@Override
	protected Class<Slider> clazz() {
		return Slider.class;
	}
	
	private boolean isEmptyUploadFile(MultipartFile image) {
		return image == null || image.getOriginalFilename().isEmpty();
	}

	@Transactional
	public Slider add(Slider s, MultipartFile sliderAvatar) throws IllegalStateException, IOException {
		String path = UPLOAD_FOLDER_ROOT + "Sliders/";

		if (!isEmptyUploadFile(sliderAvatar)) {
			String pathSub = s.getId() + "";
			if (new File(path + pathSub).mkdirs()) {
				sliderAvatar.transferTo(new File(path + pathSub + "/" + sliderAvatar.getOriginalFilename()));

				s.setImage(pathSub + "/" + sliderAvatar.getOriginalFilename());
			}
		}

		s.setSeo(new Slugify().slugify(s.getName()));

		return super.saveOrUpdate(s);
	}

	@Transactional
	public Slider update(Slider s, MultipartFile sliderAvatar) throws IllegalStateException, IOException {
		String path = UPLOAD_FOLDER_ROOT + "Sliders/";
		Slider sliderInDb = super.getById(s.getId());

		if (!isEmptyUploadFile(sliderAvatar)) {
			String pathSub = s.getId() + "/";
			new File(path + sliderInDb.getImage()).delete();

			sliderAvatar.transferTo(new File(path + pathSub + sliderAvatar.getOriginalFilename()));
			s.setImage(pathSub + sliderAvatar.getOriginalFilename());
		} else {
			s.setImage(sliderInDb.getImage());
		}

		s.setSeo(new Slugify().slugify(s.getName()));

		return super.saveOrUpdate(s);
	}

}
