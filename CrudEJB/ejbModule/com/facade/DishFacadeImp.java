package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.DishDAO;
import com.model.Dish;

@Stateless
public class DishFacadeImp implements DishFacade {
	
	@EJB
	private DishDAO dishDAO;
	
	@Override
	public void save(Dish dish) {
		isDishWithAllData(dish);
		
		dishDAO.save(dish);
	}

	@Override
	public Dish update(Dish dish) {
		isDishWithAllData(dish);
		
		return dishDAO.update(dish);
	}
	
	@Override
	public void delete(Dish dish) {
		dishDAO.delete(dish);
	}

	@Override
	public Dish find(int entityID) {
		return dishDAO.find(entityID);
	}

	@Override
	public List<Dish> findAll() {
		return dishDAO.findAll();
	}
	
	private void isDishWithAllData(Dish dish){
		boolean hasError = false;
		
		if(dish == null){
			hasError = true;
		}
		
		if (dish.getName() == null || "".equals(dish.getName().trim())){
			hasError = true;
		}
		
		if(dish.getPrice() <= 0){
			hasError = true;
		}
		
		if (hasError){
			throw new IllegalArgumentException("The dish is missing data. Check the name and price, they should have value.");
		}
	}
}