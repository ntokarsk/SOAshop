package com.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
/*	@OneToMany(mappedBy="order")
	private List<Dish> dishes;*/

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
/*	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}*/
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}