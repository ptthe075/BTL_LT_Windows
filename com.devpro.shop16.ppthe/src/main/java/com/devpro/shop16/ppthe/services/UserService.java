package com.devpro.shop16.ppthe.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.devpro.shop16.ppthe.entities.Role;
import com.devpro.shop16.ppthe.entities.User;

@Service
public class UserService extends BaseService<User> {

	@Override
	protected Class<User> clazz() {
		return User.class;
	}
	
	public User loadUserByUsername(String userName) {
		String sql = "select * from tbl_user u where u.username = '" + userName + "'";
		List<User> users = this.executeByNativeSQL(sql, 0).getData();
		
		if(users == null || users.size() <= 0) return null;
		return users.get(0);
	}

	public List<User> findByRole(String nameRole) {
		List<User> users = this.findAll();
		List<User> newUsers = new ArrayList<User>();
		
		for (User u : users) {
			Set<Role> roles = u.getRoles();
			for (Role r : roles) {
				if(r.getName().equals(nameRole)) {
					newUsers.add(u);
				}
			}
		}
		
		return newUsers;
	}

}
