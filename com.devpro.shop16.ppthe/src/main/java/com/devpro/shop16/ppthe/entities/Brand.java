package com.devpro.shop16.ppthe.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_brand")
public class Brand extends BaseEntity {
	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "image", length = 100, nullable = true)
	private String image;

	@Column(name = "seo", length = 1000, nullable = true)
	private String seo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSeo() {
		return seo;
	}

	public void setSeo(String seo) {
		this.seo = seo;
	}

}
