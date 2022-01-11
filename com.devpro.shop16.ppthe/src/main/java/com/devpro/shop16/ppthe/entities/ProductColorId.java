package com.devpro.shop16.ppthe.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
@SuppressWarnings("serial")
public class ProductColorId implements Serializable {

	@ManyToOne(cascade = CascadeType.ALL)
	private Product product;

	@ManyToOne(cascade = CascadeType.ALL)
	private Color color;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
