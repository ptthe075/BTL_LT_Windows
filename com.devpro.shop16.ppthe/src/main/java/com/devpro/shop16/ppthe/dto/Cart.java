package com.devpro.shop16.ppthe.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * giỏ hàng
 */
public class Cart {

	private BigDecimal totalPrice = BigDecimal.ZERO;
	
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

}
