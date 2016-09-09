package catalog;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.facade.CategoriesFacade;
import com.model.Categories;
import com.model.Ingredient;
import com.facade.IngredientFacade;
import com.model.Dish;
import com.facade.DishFacade;

@ManagedBean
@RequestScoped
public class CategoriesController {
	

	@EJB
	private DishFacade dishFacade;
	@EJB
	private IngredientFacade ingredientFacade;
	@EJB
	private CategoriesFacade categoriesFacade;
	
	private static final String CREATE_CATEGORIES = "createCategories";
	private static final String DELETE_CATEGORIES = "deleteCategories"; 
	private static final String UPDATE_CATEGORIES = "updateCategories";
	private static final String LIST_ALL_CATEGORIES = "categories";
	private static final String STAY_IN_THE_SAME_PAGE = null;

	private Categories categories;

	public Categories getCategories() {
		
		if(categories == null){
			categories = new Categories();
		}
		
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public List<Categories> getAllCategories() {
		return categoriesFacade.findAll();
	}

	public String updateCategoriesStart(){
		return UPDATE_CATEGORIES;
	}
	
	public String updateCategoriesEnd(){
		try {
			categoriesFacade.update(categories);
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Check if the weight is above 0 or call the adm");
			return STAY_IN_THE_SAME_PAGE;
		}
		
		sendInfoMessageToUser("Operation Complete: Update");
		return LIST_ALL_CATEGORIES;
	}
	
	public String deleteCategoriesStart(){
		return DELETE_CATEGORIES;
	}
	
/*	public String deleteCategoriesEnd(){
		try {
			categoriesFacade.delete(categories);
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Call the ADM");
			return STAY_IN_THE_SAME_PAGE;
		}			
		
		sendInfoMessageToUser("Operation Complete: Delete");
		
		return LIST_ALL_CATEGORIES;
	}*/
	
	//DANGER ZONE START

	private Ingredient ingredient;

	public Ingredient getIngredient() {
		if (ingredient == null) {
			ingredient = new Ingredient();
		}
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
	//DANGER ZONE END
	
	public String deleteCategoriesEnd(Categories categories1) {
		System.err.println("jestem tutaj kurwo");
		for (Dish dish : dishFacade.findAll()) {
			System.err.println("jestem tutaj kurwo FOR");
			if (categories1.getId() == dish.getCategories().getId()) {
				System.err.println("jestem tutaj kurwo IF");
				for (Ingredient ingredient : ingredientFacade.findAll()) {
					System.err.println("jestem tutaj kurwo DRUGI FOR");
					if (dish.getId() == ingredient.getDish().getId()) {
						System.err.println("jestem tutaj kurwo DRUGI IF");
						ingredientFacade.delete(ingredient);
					}
				}
				dishFacade.delete(dish);
			}
		}
		try {
			categoriesFacade.delete(categories1);
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Problem with categories delete operation");
			return STAY_IN_THE_SAME_PAGE;
		}

		sendInfoMessageToUser("Operation Complete: Delete");

		return "STAY_IN_THE_SAME_PAGE";
	}
	
	
	public String createCategoriesStart(){
		return CREATE_CATEGORIES;
	}
	
	public String createCategoriesEnd() {
		try {
			categoriesFacade.update(categories);
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Problem with category create operation");
			return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Create");
		return "cateringMenu";
	}
	
	public String listAllCategories(){
		return LIST_ALL_CATEGORIES;
	}
	
	private void sendInfoMessageToUser(String message){
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}
	
	private void sendErrorMessageToUser(String message){
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}
	
	private FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
	}
	
	// DANGER ZONE START
	private static final String CREATE_DISH = "createDish";
	private static final String DELETE_DISH = "deleteDish"; 
	private static final String UPDATE_DISH = "updateDish";
	//private static final String LIST_ALL_DISH = "dish";

	private Dish dish;

	public Dish getDish() {
		
		if(dish == null){
			dish = new Dish();
		}
		
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public List<Dish> getAllDish() {
		return dishFacade.findAll();
	}
	
	public List<Dish> getAllDishByCat(int category_id) {
		List<Dish> list = getAllDish();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getCategories().getId() != category_id)
				list.remove(i);
		}
		return list;
	}
	
	public String updateDishStart(){
		return UPDATE_DISH;
	}
	
	public String updateDishEnd(){
		try {
			dishFacade.update(dish);
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Check if the weight is above 0 or call the adm");
			return STAY_IN_THE_SAME_PAGE;
		}
		
		sendInfoMessageToUser("Operation Complete: Update");
		return STAY_IN_THE_SAME_PAGE;
	}
	
	public String deleteDishStart(){
		return DELETE_DISH;
	}
	
	public String deleteDishEnd(){
		try {
			dishFacade.delete(dish);
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Call the ADM");
			return STAY_IN_THE_SAME_PAGE;
		}			
		
		sendInfoMessageToUser("Operation Complete: Delete");
		
		return STAY_IN_THE_SAME_PAGE;
	}
	
	public String deleteMealStart(Dish dish) {
		for (Ingredient ingredient : ingredientFacade.findAll()) {
			if (dish.getId() == ingredient.getDish().getId()) {
				ingredientFacade.delete(ingredient);
			}
		}
		dishFacade.delete(dish);
		return STAY_IN_THE_SAME_PAGE;
	}
	
	
	public String createDishStart(){
		return CREATE_DISH;
	}
	
	public String createDishEnd(){
		try {
			dishFacade.save(dish);
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Check if the weight is above 0 or call the adm");
			
			return STAY_IN_THE_SAME_PAGE;
		}		
		
		sendInfoMessageToUser("Operation Complete: Create");
		
		return STAY_IN_THE_SAME_PAGE;
	}
	
	public String listAllDish(){
		return STAY_IN_THE_SAME_PAGE;
	}
	
	public List<Ingredient> getAllIngredients() {
		return ingredientFacade.findAll();
	}
	// DANGER ZONE END
	

	public String getNewCategory(){
		return "createCategory";
	}
}