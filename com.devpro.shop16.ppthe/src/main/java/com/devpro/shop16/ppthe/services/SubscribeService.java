package com.devpro.shop16.ppthe.services;

import org.springframework.stereotype.Service;

import com.devpro.shop16.ppthe.entities.Subscribe;

@Service
public class SubscribeService extends BaseService<Subscribe>{

	@Override
	protected Class<Subscribe> clazz() {
		return Subscribe.class;
	}

}
