package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.CategoriesDAO;
import com.model.Categories;

@Stateless
public class CategoriesFacadeImp implements CategoriesFacade {
	
	@EJB
	private CategoriesDAO categoriesDAO;
	
	@Override
	public void save(Categories categories) {
		isCategoriesWithAllData(categories);
		
		categoriesDAO.save(categories);
	}

	@Override
	public Categories update(Categories categories) {
		isCategoriesWithAllData(categories);
		
		return categoriesDAO.update(categories);
	}
	
	@Override
	public void delete(Categories categories) {
		categoriesDAO.delete(categories);
	}

	@Override
	public Categories find(int entityID) {
		return categoriesDAO.find(entityID);
	}

	@Override
	public List<Categories> findAll() {
		return categoriesDAO.findAll();
	}
	
	private void isCategoriesWithAllData(Categories categories){
		boolean hasError = false;
		
		if(categories == null){
			hasError = true;
		}
		
		if (categories.getName() == null || "".equals(categories.getName().trim())){
			hasError = true;
		}
		
		if (hasError){
			throw new IllegalArgumentException("The categories is missing data. Check the name and price, they should have value.");
		}
	}
}