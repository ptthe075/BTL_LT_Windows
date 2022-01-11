package com.devpro.shop16.ppthe.services;

import org.springframework.stereotype.Service;

import com.devpro.shop16.ppthe.entities.SaleOrder;

@Service
public class SaleOrderService extends BaseService<SaleOrder> {

	@Override
	protected Class<SaleOrder> clazz() {
		return SaleOrder.class;
	}

}
