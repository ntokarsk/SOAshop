package com.facade;

import java.util.List;

import javax.ejb.Local;

import com.model.Order;

@Local
public interface OrderFacade {

	public abstract void save(Order order);

	public abstract Order update(Order order);
	
	public abstract void delete(Order order);

	public abstract Order find(int entityID);

	public abstract List<Order> findAll();
}