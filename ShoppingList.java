import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class ShoppingList {

	public static void main(String[] args) {
		
		Scanner scnr = new Scanner (System.in);
		
		boolean userInput = false;
		
		ArrayList<String> shoppingCart = new ArrayList<>();
		ArrayList<Double> price = new ArrayList<>();
		ArrayList<Integer> amount = new ArrayList<>();
		
		TreeMap<Integer, String> stockList = stockList1();
		TreeMap<String,Double> cost = price1();
		int userPick = -1;
		
		System.out.println("Welcome to Guenther's Market! ");
		String yesOrNo="Y";
		
		menu(cost);
		do {
			System.out.println();
			System.out.println("Which item would you like to order? (please pick by number).");
			userPick = scnr.nextInt();
			addItem(userPick, shoppingCart, amount, price, cost, stockList);
			System.out.println("Would you like to order anything else? (Y/N)");
			scnr.nextLine();
			yesOrNo = scnr.nextLine();
			userInput = answer(yesOrNo);
		} while (userInput);
		
		customerCheckOut(shoppingCart, amount, price);
		scnr.close();
	}


	private static TreeMap<String, Double> price1() {
		
		TreeMap<String,Double> items = new TreeMap<>();
		items.put("apple", 0.99);
		items.put("banana", 0.59);
		items.put("cauliflower", 1.59);
		items.put("dragonfruit", 2.19);
		items.put("elderberry", 1.79);
		items.put("figs", 2.09);
		items.put("grapefruit", 1.99);
		items.put("honeydew", 3.49);
		return items;
	}

	private static TreeMap<Integer, String> stockList1() {
		
		TreeMap<Integer, String> index = new TreeMap<>();
		index.put(1, "apple");
		index.put(2, "banana");
		index.put(3, "cauliflower");
		index.put(4, "dragonfruit");
		index.put(5,  "elderberry");
		index.put(6, "figs");
		index.put(7, "grapefruit");
		index.put(8, "honeydew");
		
		return index;
	}

	private static void customerCheckOut(ArrayList<String> shoppingCart, ArrayList<Integer> amount,
			ArrayList<Double> price) {
		System.out.println("Thank you for your order!\n");
		System.out.println("Here's what is in your cart: \n");
		
		for (int j = 0; j < shoppingCart.size(); j++) {
			System.out.printf("%-5s%3d at $%-5.2fea. $%-5.2f\n", shoppingCart.get(j),
					amount.get(j), price.get(j),(amount.get(j)*price.get(j)));
		}
		average(shoppingCart, amount, price);
	}
	

	private static boolean answer(String yesOrNo) {
			
			if(yesOrNo.equalsIgnoreCase("N")) 
				return false;
			
	  
			return true;
			
	}

	private static void addItem(int userPick, ArrayList<String> shoppingCart, ArrayList<Integer> amount,
		ArrayList<Double> price, TreeMap<String, Double> cost, TreeMap<Integer, String> stockList) {
		
		try {
			System.out.printf("Adding " + stockList.get(userPick) + " to your cart at $%.2f\n" ,
					cost.get(stockList.get(userPick)));
			if (shoppingCart.indexOf(stockList.get(userPick)) == -1) { 
				shoppingCart.add(stockList.get(userPick));
				amount.add(1);
				price.add(cost.get(stockList.get(userPick)));
			} else { 
				amount.set(shoppingCart.indexOf(stockList.get(userPick)), 
						amount.get(shoppingCart.indexOf(stockList.get(userPick))) + 1);
			}
		} catch(NullPointerException e){ 
			System.out.println("The item does not exist!");
		}
	}
		
	
	
	private static void menu(TreeMap<String, Double> cost) {
		
		System.out.printf("\n%-15s%-15s\n==============================\n", "Item", "Price");
		Set<String> setOfKeys = cost.keySet();
		int i = 1;
		for (String item : setOfKeys) {
			System.out.printf("%-5d%-15s$%-15.2f\n", i++, item, cost.get(item));
		}
	}
	
	public static void average(ArrayList<String> shoppingCart, 
			ArrayList<Integer> amount, ArrayList<Double> price) {
		
		double subTotal = 0.0;
		for (int i = 0; i < amount.size(); i++) {
			subTotal += (amount.get(i) * price.get(i));
		}
		double average = subTotal / amount.size();
		System.out.println();
		System.out.printf("The average value of the items is : $%.2f\n", average);
	}
}
