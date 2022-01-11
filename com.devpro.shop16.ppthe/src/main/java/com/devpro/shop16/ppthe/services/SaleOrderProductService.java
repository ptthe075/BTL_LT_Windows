package com.devpro.shop16.ppthe.services;

import org.springframework.stereotype.Service;

import com.devpro.shop16.ppthe.entities.SaleOrderProduct;

@Service
public class SaleOrderProductService extends BaseService<SaleOrderProduct> {

	@Override
	protected Class<SaleOrderProduct> clazz() {
		return SaleOrderProduct.class;
	}

}
