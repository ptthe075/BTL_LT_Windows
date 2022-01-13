package com.devpro.shop16.ppthe.services;

import org.springframework.stereotype.Service;

import com.devpro.shop16.ppthe.entities.Information;

@Service
public class InformationService extends BaseService<Information> {

	@Override
	protected Class<Information> clazz() {
		return Information.class;
	}
}
