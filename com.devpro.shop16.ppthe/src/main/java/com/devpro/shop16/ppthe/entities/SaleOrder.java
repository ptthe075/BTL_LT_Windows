package com.devpro.shop16.ppthe.entities;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_saleorder")
public class SaleOrder extends BaseEntity {
	@Column(name = "code", length = 45, nullable = false)
	private String code;

	@Column(name = "user_id", nullable = true)
	private Integer userId;

	@Column(name = "total", precision = 13, scale = 2, nullable = true)
	private BigDecimal total;

	@Column(name = "customer_name", length = 100, nullable = true)
	private String customerName;

	@Column(name = "customer_address", length = 100, nullable = true)
	private String customerAddress;

	@Column(name = "customer_phone", length = 100, nullable = true)
	private String customerPhone;

	@Column(name = "customer_email", length = 100, nullable = true)
	private String customerEmail;

	@Column(name = "seo", length = 200, nullable = true)
	private String seo;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "saleOrder", fetch = FetchType.LAZY)
	private Set<SaleOrderProduct> saleOrderProducts = new HashSet<SaleOrderProduct>();

	public void addSaleOrderProduct(SaleOrderProduct saleOrderProduct) {
		this.saleOrderProducts.add(saleOrderProduct);
		saleOrderProduct.setSaleOrder(this);
	}

	public void SaleOrderProduct(SaleOrderProduct saleOrderProduct) {
		this.saleOrderProducts.remove(saleOrderProduct);
		saleOrderProduct.setSaleOrder(null);
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getSeo() {
		return seo;
	}

	public void setSeo(String seo) {
		this.seo = seo;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

}
