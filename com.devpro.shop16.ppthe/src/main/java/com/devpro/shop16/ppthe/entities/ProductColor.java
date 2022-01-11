package com.devpro.shop16.ppthe.entities;

import java.math.BigDecimal;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_product_color")
@AssociationOverrides({ @AssociationOverride(name = "pk.product", joinColumns = @JoinColumn(name = "product_id")),
		@AssociationOverride(name = "pk.color", joinColumns = @JoinColumn(name = "color_id")) })
public class ProductColor {
	@EmbeddedId
	private ProductColorId pk = new ProductColorId();

	@Column(name = "price_extra", precision = 13, scale = 2, nullable = true)
	private BigDecimal priceExtra;

	public ProductColorId getPk() {
		return pk;
	}

	public void setPk(ProductColorId pk) {
		this.pk = pk;
	}

	public BigDecimal getPriceExtra() {
		return priceExtra;
	}

	public void setPriceExtra(BigDecimal priceExtra) {
		this.priceExtra = priceExtra;
	}

}
