import java.util.ArrayList;

public class OrderPrice {
	
	public ArrayList<String> order;
	
	/**
	 * Empty constructor 
	 */
	public OrderPrice() {
		this.order = new ArrayList<String>();
	}
	
	/**
	 * Constructor with parameter
	 * @param order
	 */
	public OrderPrice(ArrayList<String> order) {
		this.order = new ArrayList<String>(order);
	}
	
	/**
	 * @return the order
	 */
	public ArrayList<String> getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(ArrayList<String> order) {
		this.order = order;
	}

	public void addDish(String dish) {
		this.order.add(dish);
	}
	
	public void clearOrder() {
		this.order.clear();
	}
	
	public void displayOrder() {
		for(int i = 0; i < this.order.size(); i++) {
			System.out.println(this.order.get(i));
		}
	}
}
