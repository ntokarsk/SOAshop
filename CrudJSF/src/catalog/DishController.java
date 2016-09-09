package catalog;

import com.facade.CategoriesFacade;
import com.facade.IngredientFacade;
import com.facade.DishFacade;
import com.model.Categories;
import com.model.Ingredient;
import com.model.Dish;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class DishController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String STAY_IN_THE_SAME_PAGE = null;

	@EJB
	private CategoriesFacade categoryFacade;

	@EJB
	private DishFacade dishFacade;

	@EJB
	private IngredientFacade ingredientFacade;

	private Categories category;

	private Ingredient ingredient;

	private Dish dish;

	public Ingredient getIngredient() {
		if (ingredient == null) {
			ingredient = new Ingredient();
		}
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Dish getDish() {
		if (dish == null) {
			dish = new Dish();
		}
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public Categories getCategories() {
		if (category == null) {
			category = new Categories();
		}
		return category;
	}

	public void setCategories(Categories category) {
		this.category = category;
	}

	public String createDishEnd() {
		try {
			dish.setCategories(category);
			/*
			 * System.err.println("dish.setCategories(category);");
			 * System.err.println("dish.getCategories().getName() = " +
			 * dish.getCategories().getId());
			 * System.err.println("dish.getName() = " + dish.getName());
			 */
			dish.setPrice(20);
			dish.setDescription("mock description");
			dishFacade.update(dish);
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Problem with dish create operation");
			return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Create");
		return STAY_IN_THE_SAME_PAGE;
	}

	private void sendInfoMessageToUser(String message) {
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				message, message));
	}

	private void sendErrorMessageToUser(String message) {
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				message, message));
	}

	private FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
	}

	public String updateCategoriesStart() {
		return "";
	}

	public List<Categories> getAllCategories() {
		return categoryFacade.findAll();
	}

	public List<Dish> getAllDishes() {
		return dishFacade.findAll();
	}

	public List<Ingredient> getAllIngredients() {
		return ingredientFacade.findAll();
	}

	public String addIngredient() {
		ingredient.setDish(dish);
		ingredientFacade.save(ingredient);
		ingredient = null;
		sendInfoMessageToUser("Operation Complete: ingredient added");
		return STAY_IN_THE_SAME_PAGE;
	}

	public String deleteIngredient(Ingredient ingredient) {
		ingredientFacade.delete(ingredient);
		ingredient = null;
		sendInfoMessageToUser("Operation Complete: ingredient removed");
		return STAY_IN_THE_SAME_PAGE;
	}
}