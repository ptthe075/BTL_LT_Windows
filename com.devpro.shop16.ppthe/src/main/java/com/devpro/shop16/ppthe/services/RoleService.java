package com.devpro.shop16.ppthe.services;

import org.springframework.stereotype.Service;

import com.devpro.shop16.ppthe.entities.Role;

@Service
public class RoleService extends BaseService<Role> {

	@Override
	protected Class<Role> clazz() {
		return Role.class;
	}

}
