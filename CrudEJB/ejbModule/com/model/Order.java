package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
//@NamedQuery(name = "Order.findOrdersByUser", query = "select * from Order o where o.id = :id")
public class Order {

	//public static final String FIND_BY_USER = "Order.findUserByEmail";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int order_id;
	private boolean payment;
	private int price;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public boolean isPayment() {
		return payment;
	}

	public void setPayment(boolean payment) {
		this.payment = payment;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return getOrder_id();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Order) {
			Order order = (Order) obj;
			return order.getOrder_id() == getOrder_id();
		}

		return false;
	}
}