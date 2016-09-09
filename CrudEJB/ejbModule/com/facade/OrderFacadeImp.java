package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.OrderDAO;
import com.model.Order;

@Stateless
public class OrderFacadeImp implements OrderFacade {
	
	@EJB
	private OrderDAO orderDAO;
	
	@Override
	public void save(Order order) {
		isOrderWithAllData(order);
		
		orderDAO.save(order);
	}

	@Override
	public Order update(Order order) {
		isOrderWithAllData(order);
		
		return orderDAO.update(order);
	}
	
	@Override
	public void delete(Order order) {
		orderDAO.delete(order);
	}

	@Override
	public Order find(int entityID) {
		return orderDAO.find(entityID);
	}

	@Override
	public List<Order> findAll() {
		return orderDAO.findAll();
	}
	
	private void isOrderWithAllData(Order order){
		boolean hasError = false;
		
		if(order == null){
			hasError = true;
		}
		
		if(order.getPrice() <= 0){
			hasError = true;
		}
		
		if (hasError){
			throw new IllegalArgumentException("The order is missing data. Check the name and price, they should have value.");
		}
	}
}