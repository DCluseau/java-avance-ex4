import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class representing a list of orders for one table
 */
public class TablePrice {
	private final String[] STARTER = { "Entrée", "Salade", "Soupe", "Quiche", "Aucune" };
	private final HashMap<String, Double> STARTER2 = new HashMap<String, Double>(){{
		put("Entrée" , 0.0);
		put("Salade" , 2.0);
		put("Soupe" , 3.5);
		put("Ouiche" , 4.7);
		put("Aucune" , 0.0);
	}};
	private final HashMap<String, Double> DISHES2 = new HashMap<String, Double>(){{
		put("Plats" , 0.0);
		put("Poulet" , 2.0);
		put("Boeuf" , 3.5);
		put("Poisson" , 4.7);
		put("Végétarien" , 1.5);
		put("Vegan" , 1.0);
		put("Aucun" , 0.0);
	}};
	private final HashMap<String, Double> SIDE_DISH2 = new HashMap<String, Double>(){{
		put("Accompagnements" , 0.0);
		put("Riz" , 2.0);
		put("Pâtes" , 3.5);
		put("Frites" , 4.7);
		put("Légumes" , 1.5);
		put("Aucun" , 0.0);
	}};
	private final HashMap<String, Double> DRINKS2 = new HashMap<String, Double>(){{
		put("Boissons" , 0.0);
		put("Eau plate" , 2.0);
		put("Eau gazeuze" , 3.5);
		put("Soda" , 4.7);
		put("Vin" , 1.5);
		put("Aucune" , 0.0);
	}};
	private final HashMap<String, Double> DESSERTS2 = new HashMap<String, Double>(){{
		put("Desserts" , 0.0);
		put("Tarte maison" , 2.0);
		put("Mousse au chocolat" , 3.5);
		put("Tiramisu" , 4.7);
		put("Aucun" , 0.0);
	}};
	private final String[] DISHES = { "Plats", "Poulet", "Boeuf", "Poisson", "Végétarien", "Vegan", "aucun" };
	private final String[] SIDE_DISH = { "Accompagnements", "Riz", "Pâtes", "Frites", "Légumes", "Aucun" };
	private final String[] DRINKS = { "Boissons", "Eau plate", "Eau gazeuze", "Soda", "Vin", "Aucune" };
	private final String[] DESSERTS = { "Desserts", "Tarte maison", "Mousse au chocolat", "Tiramisu", "aucun" };

	ArrayList<Order> orders;
	/**
	 * Constructor
	 */
	public TablePrice() {
		this.orders = new ArrayList<Order>();
	}
	
	/**
	 * Display the available dishes
	 * @param table
	 */
	public void displayTable(String[] table) {
		for (int i = 1; i < table.length; i++) {
			System.out.print("[" + i + " - " + table[i].toUpperCase() + "]");
		}
		System.out.println();
	}
	
	/**
	 * Get customer's order
	 */
	public void getOrders() {
		Order order = new Order();
		System.out.println("bonjour, combien de menus souhaitez vous ?");
		Scanner scan = new Scanner(System.in);
		int nbMenu = 0;
		try {
			while (scan.hasNextInt() == false) {
				scan.next();
			}	
			nbMenu = scan.nextInt();
		}catch(Exception e){
			System.out.println("Erreur de saisie : " + e.getMessage());
		}
			
		for (int i = 0; i < nbMenu; i++) {
			System.out.println("Commande numéro " + (i + 1));
			int result = this.getInfos(scan, STARTER[0]);
			if (STARTER.length - 1 > result)
				order.addDish(STARTER[result]);

			result = this.getInfos(scan, DISHES[0]);
			if (DISHES.length - 1 > result)
				order.addDish(DISHES[result]);

			result = this.getInfos(scan, SIDE_DISH[0]);
			if (SIDE_DISH.length - 1 > result)
				order.addDish(SIDE_DISH[result]);

			result = this.getInfos(scan, DRINKS[0]);
			if (DRINKS.length - 1 > result)
				order.addDish(DRINKS[result]);

			result = this.getInfos(scan, DESSERTS[0]);
			if (DESSERTS.length - 1 > result)
				order.addDish(DESSERTS[result]);

			System.out.println("Résumé de la commande " + (i + 1));
			order.displayOrder(); // ici on pourrait stocker la commande en base par exemple
			System.out.println(); // avant de passer à la suivante
			this.orders.add(new Order(order.getOrder()));
			order.clearOrder();
		}
		scan.close();
		this.printTicket();
	}
	
	/**
	 * Get list of items in the menu, depending on the type of dish
	 * @param scan
	 * @param info
	 * @return what the user chose
	 */
	public int getInfos(Scanner scan, String info) {
		int choice = 0;
		System.out.println("choix " + info + " : ");
		if (info.equalsIgnoreCase(this.STARTER[0]))
			this.displayTable(this.STARTER);
		else if (info.equalsIgnoreCase(this.DISHES[0]))
			this.displayTable(this.DISHES);
		else if (info.equalsIgnoreCase(this.SIDE_DISH[0]))
			this.displayTable(this.SIDE_DISH);
		else if (info.equalsIgnoreCase(this.DRINKS[0]))
			displayTable(this.DRINKS);
		else if (info.equalsIgnoreCase(this.DESSERTS[0]))
			this.displayTable(this.DESSERTS);
		System.out.println("que souhaitez vous comme " + info + " ? [saisir le chiffre correspondant]");
		try {
			choice = scan.nextInt(); 
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return choice;
	}
	
	/**
	 * Print orders ticket
	 */
	public void printTicket() {
		FileWriter myWriter;
		try {
			myWriter = new FileWriter("ticket.txt");
			for (int i = 0; i < this.orders.size(); i++) {
				myWriter.write("******************** Résumé de la commande N° " + (i + 1) + " ********************\n");
				for(String dish : this.orders.get(i).getOrder()) {
					myWriter.write(dish + "\n");
				}
				  myWriter.write("\n");
			}
			myWriter.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
