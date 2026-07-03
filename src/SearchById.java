package app;

import java.util.*;

public class SearchById implements SearchMethod {
	private final Scanner consoleInput = new Scanner(System.in);
    /**
     * Searches for products by their ID number.
     *
     * @param inventory the list of products to search through
     * @throws NumberFormatException If user doesn't input an integer value
     * @return a list of products matching the specified ID
     */
    @Override
    public List<Product> filter(List<Product> inventory) {        
        System.out.print("Enter a product ID: ");
        try {
            int id = Integer.parseInt(consoleInput.nextLine().trim());
            List<Product>matches = new ArrayList<>();

            for (Product product : inventory) {
                if (product.getProductId() == id && product.getQuantityInStock() > 0) {
                    matches.add(product);
                }
            }
            return matches;
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID number");
            return Collections.emptyList();
        }
    }
}