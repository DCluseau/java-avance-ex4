import java.util.HashMap;

public class OrderPrice {
	
	//private ArrayList<String> order;
	private HashMap<String, Double> order;
	
	/**
	 * Empty constructor 
	 */
	public OrderPrice() {
		this.order = new HashMap<String, Double>();
	}
	
	/**
	 * Constructor with parameter
	 * @param order
	 */
	public OrderPrice(HashMap<String, Double> order) {
		this.order = new HashMap<String, Double>(order);
	}
	
	/**
	 * @return the order
	 */
	public HashMap<String, Double> getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(HashMap<String, Double> order) {
		this.order = order;
	}

	public void addDish(String dish, Double price) {
		this.order.put(dish, price);
	}
	
	public void clearOrder() {
		this.order.clear();
	}
	
	public void displayOrder() {
		this.order.forEach((key, value) -> {System.out.println(key + " --------- " + value + "0€\n");});
	}
	
	public String toString() {
		String finalDisplay = "";
		for(String key : this.order.keySet()) {
			finalDisplay += key + "-----" + this.order.get(key) + "0 €\n";;
		}
		return finalDisplay;
		
	}
}
