package com.devpro.shop16.ppthe.entities;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_product")
public class Product extends BaseEntity {

	@Column(name = "name", length = 1000, nullable = false)
	private String name;

	@Column(name = "avatar", length = 200, nullable = true)
	private String avatar;

	@Column(name = "price", precision = 13, scale = 2, nullable = false)
	private BigDecimal price;

	@Column(name = "price_sale", precision = 13, scale = 2, nullable = true)
	private BigDecimal priceSale;

	@Lob
	@Column(name = "specifications", columnDefinition = "LONGTEXT", nullable = true)
	private String specifications;

	@Lob
	@Column(name = "description", columnDefinition = "LONGTEXT", nullable = true)
	private String description;

	@Column(name = "is_hot", nullable = true)
	private Boolean isHot = Boolean.FALSE;

	@Column(name = "seo", length = 1000, nullable = true)
	private String seo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id") // định nghĩa khóa ngoài bằng @JoinColumn
	private Category category;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "brand_id")
	private Brand brand;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.LAZY)
	private Set<ProductImage> productImages = new HashSet<ProductImage>();

	public void addProductImage(ProductImage productImage) {
		this.productImages.add(productImage);
		productImage.setProduct(this);
	}

	public void deleteProductImage(ProductImage productImage) {
		this.productImages.remove(productImage);
		productImage.setProduct(null);
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.LAZY)
	private Set<SaleOrderProduct> saleorderProducts = new HashSet<SaleOrderProduct>();

	public void addSaleorderProduct(SaleOrderProduct saleorderProduct) {
		this.saleorderProducts.add(saleorderProduct);
		saleorderProduct.setProduct(this);
	}

	public void deleteSaleorderProduct(SaleOrderProduct saleorderProduct) {
		this.saleorderProducts.remove(saleorderProduct);
		saleorderProduct.setProduct(null);
	}
	
	@OneToMany(mappedBy = "pk.product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ProductColor> productColors = new HashSet<ProductColor>();
	
	public void addProductColor(ProductColor productColor) {
		this.productColors.add(productColor);
		productColor.getPk().setProduct(this);
	}

	public void deleteProductColor(ProductColor productColor) {
		this.productColors.remove(productColor);
		productColor.getPk().setProduct(null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPriceSale() {
		return priceSale;
	}

	public void setPriceSale(BigDecimal priceSale) {
		this.priceSale = priceSale;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsHot() {
		return isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	public String getSeo() {
		return seo;
	}

	public void setSeo(String seo) {
		this.seo = seo;
	}

	public Category getCategory() {
		return category;
	}
	
	public Integer getCategoryId() {
		return category.getId();
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Set<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(Set<ProductImage> productImages) {
		this.productImages = productImages;
	}

	public Set<SaleOrderProduct> getSaleorderProducts() {
		return saleorderProducts;
	}

	public void setSaleorderProducts(Set<SaleOrderProduct> saleorderProducts) {
		this.saleorderProducts = saleorderProducts;
	}

	public Set<ProductColor> getProductColors() {
		return productColors;
	}

	public void setProductColors(Set<ProductColor> productColors) {
		this.productColors = productColors;
	}

}
