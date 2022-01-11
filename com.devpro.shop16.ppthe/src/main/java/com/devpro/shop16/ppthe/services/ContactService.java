package com.devpro.shop16.ppthe.services;

import org.springframework.stereotype.Service;

import com.devpro.shop16.ppthe.entities.Contact;

@Service
public class ContactService extends BaseService<Contact>{

	@Override
	protected Class<Contact> clazz() {
		return Contact.class;
	}

}
