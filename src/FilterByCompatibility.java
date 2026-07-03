package app;

import java.util.*;

public class FilterByCompatibility implements SearchMethod {
	private final Scanner consoleInput = new Scanner(System.in);
    /**
     * Searches for products by their compatibility type.
     *
     * @param inventory the list of products to search through
     * @return a list of products matching the specified compatibility type
     */
    @Override
    public List<Product> filter(List<Product> inventory) {        
        System.out.print("Enter compatibility type: ");
        String compatible = consoleInput.nextLine().toLowerCase().trim();

        List<Product>matches = new ArrayList<>();

        if (compatible.isEmpty()) return matches;
        
        for (Product product : inventory) {
        	if (product.getQuantityInStock() <= 0) continue;

            if (product instanceof Accessory accessory) {
                if (accessory.getCompatibility().toLowerCase().contains(compatible)) {
                    matches.add(product);
                }
            }
        }
        return matches;
    }
}