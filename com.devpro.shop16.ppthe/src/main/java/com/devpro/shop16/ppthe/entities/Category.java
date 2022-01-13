package com.devpro.shop16.ppthe.entities;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_category")
public class Category extends BaseEntity {

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "image", length = 100, nullable = true)
	private String image;
	
	@Column(name = "seo", length = 1000, nullable = true)
	private String seo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id")
	private Category parent;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "parent")
	private Set<Category> childs = new HashSet<Category>();

	public void addChild(Category category) {
		this.childs.add(category);
		category.setParent(this);
	}

	public void deleteChild(Category category) {
		this.childs.remove(category);
		category.setParent(null);
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.LAZY)
	private Set<Product> products = new HashSet<Product>();

	public void addProduct(Product product) {
		this.products.add(product);
		product.setCategory(this);
	}

	public void deleteProduct(Product product) {
		this.products.remove(product);
		product.setCategory(null);
	}
	
	public boolean hasParentCategory(Category c) {
		Category category = this.getParent();
		if(category == null) {
			return false;
		}else {
			if(category.getId() == c.getId()) {
				return true;
			}else {
				return category.hasParentCategory(c);
			}
		}
	}
	
	public LinkedList<Category> listParentCategories(LinkedList<Category> list) {
		Category category = this.getParent();
		if(category == null) {
			return list;
		}else {
			list.addFirst(category);
			return category.listParentCategories(list);
		}
	}

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

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Set<Category> getChilds() {
		return childs;
	}

	public void setChilds(Set<Category> childs) {
		this.childs = childs;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
