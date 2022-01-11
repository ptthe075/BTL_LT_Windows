package com.devpro.shop16.ppthe.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_color")
public class Color extends BaseEntity {
	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "hex_code", length = 7, nullable = false)
	private String hexCode;

	@OneToMany(mappedBy = "pk.color", cascade = CascadeType.ALL)
	private Set<ProductColor> productColors = new HashSet<ProductColor>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHexCode() {
		return hexCode;
	}

	public void setHexCode(String hexCode) {
		this.hexCode = hexCode;
	}

	public Set<ProductColor> getProductColors() {
		return productColors;
	}

	public void setProductColors(Set<ProductColor> productColors) {
		this.productColors = productColors;
	}

}
