package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.IngredientDAO;
import com.model.Ingredient;

@Stateless
public class IngredientFacadeImp implements IngredientFacade {
	
	@EJB
	private IngredientDAO ingredientDAO;
	
	@Override
	public void save(Ingredient ingredient) {
		isIngredientWithAllData(ingredient);
		
		ingredientDAO.save(ingredient);
	}

	@Override
	public Ingredient update(Ingredient ingredient) {
		isIngredientWithAllData(ingredient);
		
		return ingredientDAO.update(ingredient);
	}
	
	@Override
	public void delete(Ingredient ingredient) {
		ingredientDAO.delete(ingredient);
	}

	@Override
	public Ingredient find(int entityID) {
		return ingredientDAO.find(entityID);
	}

	@Override
	public List<Ingredient> findAll() {
		return ingredientDAO.findAll();
	}
	
	private void isIngredientWithAllData(Ingredient ingredient){
		boolean hasError = false;
		
		if(ingredient == null){
			hasError = true;
		}
		
		if (ingredient.getName() == null || "".equals(ingredient.getName().trim())){
			hasError = true;
		}
		
		if (hasError){
			throw new IllegalArgumentException("The ingredient is missing data. Check the name and price, they should have value.");
		}
	}
}