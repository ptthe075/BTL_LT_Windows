package com.devpro.shop16.ppthe.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_subscribe")
public class Subscribe extends BaseEntity {
	@Column(name = "email", length = 200, nullable = false)
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
