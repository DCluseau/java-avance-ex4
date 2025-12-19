import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class representing a list of orders for one table
 */
public class TablePrice {
	private final HashMap<String, Double> STARTER2 = new HashMap<String, Double>(){{
		put("Entrée" , 0.0);
		put("Mensonge" , 2.0);
		put("Les clés de ta 106" , 3.5);
		put("Ouiche" , 4.7);
		put("Aucune" , 0.0);
	}};
	private final HashMap<String, Double> DISHES2 = new HashMap<String, Double>(){{
		put("Plats" , 0.0);
		put("Flic" , 2.0);
		put("Boeuf Lok-Lak" , 3.5);
		put("Poisson d'Ordralphabétix" , 4.7);
		put("Oeuf de cent ans" , 1.5);
		put("Pelouse tondue" , 1.0);
		put("Aucun" , 0.0);
	}};
	private final HashMap<String, Double> SIDE_DISH2 = new HashMap<String, Double>(){{
		put("Accompagnements" , 0.0);
		put("Chili con padre" , 2.0);
		put("Beurre salé" , 3.5);
		put("Frites du McDo" , 4.7);
		put("Truc vert pas bon" , 1.5);
		put("Aucun" , 0.0);
	}};
	private final HashMap<String, Double> DRINKS2 = new HashMap<String, Double>(){{
		put("Boissons" , 0.0);
		put("Crêpe aquatique" , 2.0);
		put("RedBull 100% taurine" , 3.5);
		put("Whisky irlandais" , 4.7);
		put("Piquette" , 1.5);
		put("Aucune" , 0.0);
	}};
	private final HashMap<String, Double> DESSERTS2 = new HashMap<String, Double>(){{
		put("Desserts" , 0.0);
		put("Tarte dans la gueule" , 2.0);
		put("Mousse au chocolat du patron" , 3.5);
		put("Dessert italien pas terrible" , 4.7);
		put("Aucun" , 0.0);
	}};

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
	public void displayTable(HashMap<String, Double> table) {
		
		for (int i = 1; i < table.size(); i++) {
			table.forEach((key, value) -> {System.out.print("[" + i + " - " + key.toUpperCase() + " ----- " + value + "0 €]");});
			//System.out.print("[" + i + " - " + table[i].toUpperCase() + "]");
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
			int result = this.getInfos(scan, "Entrées");
			if (STARTER2.size() - 1 > result)
				order.addDish(STARTER2[result]);

			result = this.getInfos(scan, "Plats");
			if (DISHES2.size() - 1 > result)
				order.addDish(DISHES2[result]);

			result = this.getInfos(scan, "Accompagnements");
			if (SIDE_DISH2.size() - 1 > result)
				order.addDish(SIDE_DISH2[result]);

			result = this.getInfos(scan, "Boissons");
			if (DRINKS2.size() - 1 > result)
				order.addDish(DRINKS2[result]);

			result = this.getInfos(scan, "Desserts");
			if (DESSERTS2.size() - 1 > result)
				order.addDish(DESSERTS2[result]);

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
		if (info.equalsIgnoreCase("Entrée"))
			this.displayTable(this.STARTER2);
		else if (info.equalsIgnoreCase("Plats"))
			this.displayTable(this.DISHES2);
		else if (info.equalsIgnoreCase("Accompagnements"))
			this.displayTable(this.SIDE_DISH2);
		else if (info.equalsIgnoreCase("Boissons"))
			displayTable(this.DRINKS2);
		else if (info.equalsIgnoreCase("Desserts"))
			this.displayTable(this.DESSERTS2);
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
