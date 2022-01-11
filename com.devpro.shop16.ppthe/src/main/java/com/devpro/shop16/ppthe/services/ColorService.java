package com.devpro.shop16.ppthe.services;

import org.springframework.stereotype.Service;

import com.devpro.shop16.ppthe.entities.Color;

@Service
public class ColorService extends BaseService<Color> {

	@Override
	protected Class<Color> clazz() {
		return Color.class;
	}

}
