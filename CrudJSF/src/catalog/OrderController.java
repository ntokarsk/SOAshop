package catalog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.facade.DishFacade;
import com.model.Dish;

@ManagedBean
@SessionScoped
public class OrderController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String STAY_IN_THE_SAME_PAGE = null;

	@EJB
	private DishFacade dishFacade;

	private Dish dish;

	private List<Dish> orderedDishesList;

	private double orderPrice = 0;

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public void addDishToCart(Dish dish) {
		if(this.orderedDishesList == null) {
			orderedDishesList = new ArrayList<Dish>();
		}
		setDish(dish);
		orderedDishesList.add(getDish());
		
		sendInfoMessageToUser("Dish added to your cart");
		
		if (dish.getPrice() != 0)
			setOrderPrice(getOrderPrice() + dish.getPrice());
	}

	public List<Dish> getOrderedDishesList() {
		return orderedDishesList;
	}

	public void addToOrderdDishesList(Dish _dish) {
		try {
			dish.setId(_dish.getId());
			dish.setCategories(_dish.getCategories());
			dish.setIngredients(_dish.getIngredients());
			dish.setName(_dish.getName());
			dish.setPrice(_dish.getPrice());
			orderedDishesList.add(dish);
			//orderedDishesList.add(new MockDish(_dish.getName(), _dish.getId(), _dish.getPrice()));
			//System.out.println("LIIIIIIIIIIIIIST SIZE = " + orderedDishesList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isListNotNull() {
		if (this.orderedDishesList != null)
			return true;
		else
			return false;
	}

	public String removeFromList(int id) {
		for (int i = 0; i < this.orderedDishesList.size(); i++) {
			if (this.orderedDishesList.get(i).getId() == id) {
				setOrderPrice(getOrderPrice() - this.orderedDishesList.get(i).getPrice());
				this.orderedDishesList.remove(i);
				sendInfoMessageToUser("Operation Complete: Delete");
			}
		}
		
		return STAY_IN_THE_SAME_PAGE;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double price) {
		this.orderPrice = price;
	}

	public DishFacade getDishFacade() {
		return dishFacade;
	}

	public void setDishFacade(DishFacade dishFacade) {
		this.dishFacade = dishFacade;
	}

	class MockDish {
		private String name;
		private int id;
		private double price;

		public MockDish(String name, int id, double price) {
			this.setName(name);
			this.setId(id);
			this.setPrice(price);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}
	}
	
	private void sendInfoMessageToUser(String message){
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}

	private FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
	}
}
