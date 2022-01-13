package com.devpro.shop16.ppthe.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_information")
public class Information extends BaseEntity {

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Lob
	@Column(name = "description", columnDefinition = "LONGTEXT", nullable = true)
	private String description;
	
	@Column(name = "seo", length = 1000, nullable = true)
	private String seo;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSeo() {
		return seo;
	}

	public void setSeo(String seo) {
		this.seo = seo;
	}

}
