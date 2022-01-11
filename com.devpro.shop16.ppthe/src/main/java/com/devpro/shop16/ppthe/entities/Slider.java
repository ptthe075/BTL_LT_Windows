package com.devpro.shop16.ppthe.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_silder")
public class Slider extends BaseEntity {
	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "image", length = 200, nullable = true)
	private String image;

	@Column(name = "link", length = 200, nullable = false)
	private String link;

	@Column(name = "seo", length = 100, nullable = true)
	private String seo;

	@Column(name = "is_hot", nullable = true)
	private Boolean isHot = Boolean.FALSE;

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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSeo() {
		return seo;
	}

	public void setSeo(String seo) {
		this.seo = seo;
	}

	public Boolean getIsHot() {
		return isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

}
