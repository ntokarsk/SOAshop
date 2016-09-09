package com.facade;

import java.util.List;

import javax.ejb.Local;

import com.model.Dish;

@Local
public interface DishFacade {

	public abstract void save(Dish dish);

	public abstract Dish update(Dish dish);
	
	public abstract void delete(Dish dish);

	public abstract Dish find(int entityID);

	public abstract List<Dish> findAll();
}