package app;

import java.util.*;

public class AdminCLI {
	private final Scanner consoleInput;

	public AdminCLI(Scanner consoleInput) {
		this.consoleInput = consoleInput;
	}

    public void run() {
    	System.out.println("ADMIN VIEW");
        
        while (true) {
	    	printAdminMenu();
	        try {
				switch (Integer.parseInt(consoleInput.nextLine().trim())) {
					case 1 -> ProductDisplay.displayProducts(true, null);
					case 2 -> addNewProduct();
					case 0 -> {
						System.out.println("Logout successful");
						return;
					}
					default -> System.out.println("Invalid input");
				} 
			} catch (NumberFormatException e) {
				System.out.println("Invalid input");
			}
        }
    }

	private void addNewProduct() {
		try {
			List<Product>inventory = ProductManager.loadProducts();
			System.out.println("ADDING NEW PRODUCT");
			
			System.out.print("Enter a unique 4-digit product ID: ");
			int id = Integer.parseInt(consoleInput.nextLine().trim());
			
			if (!String.valueOf(id).matches("\\d{4}")) {
				System.out.println("Invalid ID format. Please enter a 4-digit number.");
				return;
			}
	
			for (Product prod : inventory) {
				if (prod.getProductId() == id) {
					System.out.println("ID already exists. Please enter a unique ID.");
					return;
				}
			}
			
			System.out.println("Select product category:");
			System.out.println("1) Boardgame");
			System.out.println("2) Accessory");
			ProductCategory category = switch (Integer.parseInt(consoleInput.nextLine().trim())) {
				case 1 -> ProductCategory.BOARDGAME;
				case 2 -> ProductCategory.ACCESSORY;
				default -> throw new Exception();
			};
			
			System.out.print("Enter a product type: ");
			String type = consoleInput.nextLine().trim();
			if (type.isEmpty()) throw new Exception();
			
			System.out.print("Enter product name: ");
			String name = consoleInput.nextLine().trim();
			if (name.isEmpty()) throw new Exception();
			
			System.out.print("Enter selling price: ");
			double price = Double.parseDouble(consoleInput.nextLine().trim());
			
			System.out.print("Enter initial stock quantity: ");
			int qty = Integer.parseInt(consoleInput.nextLine().trim());
			
			System.out.print("Enter purchase cost: ");
			double cost = Double.parseDouble(consoleInput.nextLine().trim());
			
			System.out.print("Enter any additional info: ");
			String extrainfo = consoleInput.nextLine().trim();
			if (extrainfo.isEmpty()) throw new Exception();
	
			Product newProduct = ProductManager.fetchProduct(id, category, type, name, price, qty, cost, extrainfo);
			
			inventory.add(newProduct);
			ProductManager.saveProducts(inventory);
	
			System.out.println("Product successfully added to stock\n");	
		} catch (Exception e ){
			System.out.println("Product not added. Fields inputted do not meet correct criteria");
		}
	}

    private static void printAdminMenu() {
        System.out.println("PLEASE SELECT ACTION BY INPUTTING THE CORRESPONDING NUMBER (or 0 for logout)");
        System.out.println("1) View all products");
        System.out.println("2) Add new product");
        System.out.println("0) Log out");
    }
}