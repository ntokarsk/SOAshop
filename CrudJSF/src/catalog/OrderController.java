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
import com.facade.OrderFacade;
import com.mkyong.client.*;
import com.model.Dish;
import com.model.Order;
import com.model.User;

@ManagedBean
@SessionScoped
public class OrderController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String STAY_IN_THE_SAME_PAGE = null;

	@EJB
	private DishFacade dishFacade;

	private Dish dish;

	private List<Dish> orderedDishesList;

	@EJB
	private OrderFacade orderFacade;
	private Order order;
	
	
	private JAXWSConnector paymentModule;
	
	private double orderPrice = 0;

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public void addDishToCart(Dish dish) {
		if (this.orderedDishesList == null) {
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

	public String payForOrder(User user) {
		if(checkPayment()) {
			registerOrder(user);
		}
		return STAY_IN_THE_SAME_PAGE;
	}
	
	public boolean checkPayment(){
		String msg = new String();
		try {
			paymentModule = new JAXWSConnector();
			msg = paymentModule.managePayment(getOrderDescription());
			sendInfoMessageToUser("Payment system: " + msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(msg.equals("accepted")) return true;
		else return false;
		
	}

	public void registerOrder(User user) {
		try {
			if(order==null){
				order = new Order();
			}
			order.setUser(user);
			order.setPayment(true);
			Integer price_now = new Integer((int)getOrderPrice());
			order.setPrice(price_now);
			String description = "";
			for (int i = 0; i < this.orderedDishesList.size(); i++) {
				description = description + this.orderedDishesList.get(i).getName() +", ";
			}
			order.setDescription(description);
			orderFacade.update(order);
			clearEverything();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void clearEverything(){
		orderedDishesList.clear();
		orderedDishesList=null;
		orderPrice = 0;
		order = null;
	}
	
	public List<Order> getOrdersByUser(User user) {
		List<Order> list = orderFacade.findAll();
		
		for(int i = 0; i < list.size(); i++){
			String tmpEmail = list.get(i).getUser().getEmail();
			if(!tmpEmail.equals(user.getEmail())){
				list.remove(i--);
			}
		}
		return list;
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
				setOrderPrice(getOrderPrice()
						- this.orderedDishesList.get(i).getPrice());
				this.orderedDishesList.remove(i);
				sendInfoMessageToUser("Operation Complete: Delete");
			}
		}
		if(this.orderedDishesList.size() == 0) 
			this.orderedDishesList = null;
		
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
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	private String getOrderDescription(){
		String orderDescription = "";
		
		for(int i=0; i < this.orderedDishesList.size(); i++){
			orderDescription += this.orderedDishesList.get(i).getName() + "\n";
		}
		orderDescription += "TOTAL: " + getOrderPrice() + " PLN";
		
		return orderDescription;
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

	private void sendInfoMessageToUser(String message) {
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				message, message));
	}

	private FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
	}


}
