package com.devpro.shop16.ppthe.dto;

import java.math.BigDecimal;

/**
 * danh sách sản phẩm.
 */
public class CartItem {
	private int productId;

	private String productName;

	private String productAvatar;

	private int quantity;

	private BigDecimal priceUnit;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductAvatar() {
		return productAvatar;
	}

	public void setProductAvatar(String productAvatar) {
		this.productAvatar = productAvatar;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(BigDecimal priceUnit) {
		this.priceUnit = priceUnit;
	}

}
