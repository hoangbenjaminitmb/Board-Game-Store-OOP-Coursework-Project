package app;

import java.io.*;
import java.util.*;

public class ProductManager {
	private static final String STOCK_FILE = "Stock.txt";
	/**
	 * Loads the inventory of products from Stock.txt. 
	 * 
	 * @throws FileNotFoundException if the Stock.txt file does not exist.
	 * @param inventory The list of products to be loaded from the Stock.txt file.
	 * @return a list of products from the inventory loaded.
	 */
    public static List<Product> loadProducts() {
		List<Product> list = new ArrayList<>();

		if (new File(STOCK_FILE).length() == 0) {
			System.out.println("There are no products in stock at the moment.");
			return list;
		}

		try (Scanner fileScanner = new Scanner(new File(STOCK_FILE))) {
			while (fileScanner.hasNextLine()) {
				String[] p = fileScanner.nextLine().split(";");
				if (p.length <8) continue;

				list.add(fetchProduct(
					Integer.parseInt(p[0].trim()), 
					ProductCategory.valueOf(p[1].trim().toUpperCase().replace(" ", "")),
					p[2].trim(),
					p[3].trim(), 
					Double.parseDouble(p[4].trim()), 
					Integer.parseInt(p[5].trim()), 
					Double.parseDouble(p[6].trim()), 
					p[7].trim()
				));
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error. No products can be loaded at this time.");
		}
		return list;
	}

	/**
	 * Handles the write and appending logic for products in the Stock.txt text file.
	 * 
	 * @param inventory The list of products to be persisted.
	 * @throws FileNotFoundException if Stock.txt cannot be read or written.
	 */
    public static void saveProducts(List<Product>inventory) {
		try (PrintWriter writer = new PrintWriter(new File(STOCK_FILE))) {
			for (Product prod : inventory) {
				if (prod == null) continue;

				String productType = switch (prod) {
					case BoardGame boardgame -> boardgame.getProductType();
					case Accessory accessory -> accessory.getProductType();
					default -> "";
				};
				
				String additionalInfo = switch (prod) {
					case BoardGame boardgame -> boardgame.getPlayerInfo();
					case Accessory accessory -> accessory.getCompatibility();
					default -> "";
				};
				
				// Formatting
				writer.printf(
					"%d; %s; %s; %s; %.2f; %d; %.2f; %s%n",
					prod.getProductId(), 
					prod.getProductCategory().name().toLowerCase().replace("boardgame", "board game"),
					productType,
					prod.getProductName(), 
					prod.getPrice(), 
					prod.getQuantityInStock(), 
					prod.getPurchaseCost(), 
					additionalInfo
				);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error saving to file " + e.getMessage());
		}
	}
    
    // Fetch information about the product
    public static Product fetchProduct(int id, ProductCategory category, String type, String name, double price, int qty, double cost, String info) {
		return switch (category) {
			case BOARDGAME -> new BoardGame(id, type, name, price, qty, cost, info);
			case ACCESSORY -> new Accessory(id, type, name, price, qty, cost, info);
			default -> throw new IllegalArgumentException("Unknown Category: " + category);
		};
	}
}