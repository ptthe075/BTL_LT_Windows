package com.devpro.shop16.ppthe.services;

import org.springframework.stereotype.Service;

import com.devpro.shop16.ppthe.entities.SaleOrderStatus;

@Service
public class SaleOrderStatusService extends BaseService<SaleOrderStatus>{

	@Override
	protected Class<SaleOrderStatus> clazz() {
		return SaleOrderStatus.class;
	}

}
