import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class representing a list of orders for one table
 */
public class Table {
	private final String[] STARTER = { "entrée", "salade", "soupe", "quiche", "aucune" };
	private final String[] DISHES = { "plats", "poulet", "boeuf", "poisson", "végétarien", "vegan", "aucun" };
	private final String[] SIDE_DISH = { "accompagnements", "riz", "pates", "frites", "légumes", "aucun" };
	private final String[] DRINKS = { "boissons", "eau plate", "eau gazeuze", "soda", "vin", "aucune" };
	private final String[] DESSERTS = { "desserts", "tarte maison", "mousse au chocolat", "tiramisu", "aucun" };

	ArrayList<Order> orders;
	/**
	 * Constructor
	 */
	public Table() {
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
		int nbMenu;
		while (scan.hasNextInt() == false) {
			scan.next();
		}	
		nbMenu = scan.nextInt();
		
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
			System.out.println(order); // ici on pourrait stocker la commande en base par exemple
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
		return scan.nextInt();
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
			e.printStackTrace();
		}
	}
}
