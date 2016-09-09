package com.dao;

import javax.ejb.Stateless;

import com.model.Categories;

@Stateless
public class CategoriesDAO extends GenericDAO<Categories> {

    public CategoriesDAO() {
    	super(Categories.class);
    }
    
    public void delete(Categories categories) {
        super.delete(categories.getId(), Categories.class);
    }
    
}