import java.util.ArrayList;
import java.util.Scanner;

public class Order {
	/**
	 * 
	 */
	private final String[] STARTER = { "entrée", "salade", "soupe", "quiche", "aucune" };
	private final String[] DISHES = { "plats", "poulet", "boeuf", "poisson", "végétarien", "vegan", "aucun" };
	private final String[] SIDE_DISH = { "accompagnements", "riz", "pates", "frites", "légumes", "aucun" };
	private final String[] DRINKS = { "boissons", "eau plate", "eau gazeuze", "soda", "vin", "aucune" };
	private final String[] DESSERTS = { "desserts", "tarte maison", "mousse au chocolat", "tiramisu", "aucun" };

	public ArrayList<String> order;
	/**
	 * 
	 */
	public Order() {
		this.order = new ArrayList<String>();
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

	/**
	 * Get list of items in the menu, depending on the type of dish
	 * @param scan
	 * @param info
	 * @return what the user chose
	 */
	public int getInfos(Scanner scan, String info) {
		System.out.println("choix " + info + " : ");
		if (info.equalsIgnoreCase(this.STARTER[0]))
			displayTable(this.STARTER);
		else if (info.equalsIgnoreCase(this.DISHES[0]))
			displayTable(this.DISHES);
		else if (info.equalsIgnoreCase(this.SIDE_DISH[0]))
			displayTable(this.SIDE_DISH);
		else if (info.equalsIgnoreCase(this.DRINKS[0]))
			displayTable(this.DRINKS);
		else if (info.equalsIgnoreCase(this.DESSERTS[0]))
			displayTable(this.DESSERTS);
		System.out.println("que souhaitez vous comme " + info + " ? [saisir le chiffre correspondant]");
		return scan.nextInt();
	}
	
	/**
	 * 
	 * @param table
	 */
	public void displayTable(String[] table) {
		for (int i = 1; i < table.length; i++) {
			System.out.print("[" + i + " - " + table[i].toUpperCase() + "]");
		}
		System.out.println();
	}
}
