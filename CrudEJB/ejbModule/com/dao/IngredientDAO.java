package com.dao;

import javax.ejb.Stateless;

import com.model.Ingredient;

@Stateless
public class IngredientDAO extends GenericDAO<Ingredient> {

    public IngredientDAO() {
    	super(Ingredient.class);
    }
    
    public void delete(Ingredient ingredient) {
        super.delete(ingredient.getId(), Ingredient.class);
    }
    
}