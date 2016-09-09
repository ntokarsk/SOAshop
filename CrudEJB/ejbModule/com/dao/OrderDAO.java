package com.dao;

import javax.ejb.Stateless;

import com.model.Order;

@Stateless
public class OrderDAO extends GenericDAO<Order> {

    public OrderDAO() {
    	super(Order.class);
    }
    
    public void delete(Order order) {
        super.delete(order.getOrder_id(), Order.class);
    }
    
}