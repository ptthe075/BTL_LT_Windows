package com.devpro.shop16.ppthe.services;

import org.springframework.stereotype.Service;

import com.devpro.shop16.ppthe.entities.ProductImage;

@Service
public class ProductImageService extends BaseService<ProductImage> {

	@Override
	protected Class<ProductImage> clazz() {
		return ProductImage.class;
	}


}
