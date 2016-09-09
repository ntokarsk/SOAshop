package com.facade;

import java.util.List;

import javax.ejb.Local;

import com.model.Categories;

@Local
public interface CategoriesFacade {

	public abstract void save(Categories categories);

	public abstract Categories update(Categories categories);
	
	public abstract void delete(Categories categories);

	public abstract Categories find(int entityID);

	public abstract List<Categories> findAll();
}