package com.devpro.shop16.ppthe.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_saleorder_status")
public class SaleOrderStatus extends BaseEntity{
	@Column(name = "name", length = 45, nullable = false)
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "saleOrderStatus", fetch = FetchType.LAZY)
	private Set<SaleOrder> saleOrders = new HashSet<SaleOrder>();

	public void addSaleOrder(SaleOrder saleOrder) {
		this.saleOrders.add(saleOrder);
		saleOrder.setSaleOrderStatus(this);
	}

	public void deleteSaleOrder(SaleOrder saleOrder) {
		this.saleOrders.remove(saleOrder);
		saleOrder.setSaleOrderStatus(null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SaleOrder> getSaleOrders() {
		return saleOrders;
	}

	public void setSaleOrders(Set<SaleOrder> saleOrders) {
		this.saleOrders = saleOrders;
	}

}
