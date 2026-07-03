package app;

import java.util.*;

public class ProductDisplay {
    private static boolean adminview;

    public static boolean isAdmin() {
        return adminview;
    }

	/**
	 * Displays an individual list of products from the Stock.txt file. 
	 *
     * @param iterator Checks whether the product is empty or not. If so, it is removed from non-admin view.
	 * @param isAdmin If true, includes products whose stock quantity is 0 and the product's "Purchase Cost". 
	 * @param searchResults Displays products based on filtering, including ID and Compatibility.
	 */
	public static void displayProducts(boolean isAdmin, List<Product> searchResults) {
		adminview = isAdmin;

        List<Product> inventory = (searchResults != null)
            ? new ArrayList<>(searchResults) 
            : ProductManager.loadProducts();
        
		if (!isAdmin) {
			Iterator<Product> iterator = inventory.iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                if (product.getQuantityInStock() == 0) {
                    iterator.remove();
                }
            }
		}

        if (inventory.isEmpty()) {
            System.out.println("There are no available products. Sorry");
            return;
        }

        // Sorts products by price descending
		inventory.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice())); 

        productTable(isAdmin, searchResults != null, inventory);
	}
    
    private static void productTable(boolean isAdmin, boolean searchResults, List<Product> inventory) {
        int width = isAdmin ? 145 : 125;
        String line = "=".repeat(width);
        
        String title = searchResults 
            ? "SEARCH RESULTS" 
            : "VIEWING ALL "+ (!isAdmin ? "AVAILABLE " : "") + "PRODUCTS (Sorted by Price Descending)"; 
        
        System.out.println(title + "\n" + line);
		String headerFormat = "%-4s | %-9s | %-13s | %-29s | %-8s | %-8s | " + (isAdmin ? "%-13s | ":"")  + "%-20s%n";

        Object[] headers = isAdmin ?
            new Object[]{"ID" , "CATEGORY" , "TYPE" , "PRODUCT NAME", "PRICE", "STOCK", "PURCHASE COST", "ADDITIONAL INFO"} :
            new Object[]{"ID" , "CATEGORY" , "TYPE" , "PRODUCT NAME", "PRICE", "STOCK", "ADDITIONAL INFO"};

        System.out.printf(headerFormat, headers);
        System.out.println(line);

        for (Product product : inventory) {
            System.out.print(product.toString());
        }
        
        System.out.println("");
    }
}