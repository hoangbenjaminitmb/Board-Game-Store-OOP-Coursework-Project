package app;

import java.util.*;

public class AddToBasket {
    private final Basket basket;
    private final Scanner consoleInput;

    public AddToBasket(Scanner consoleInput, Basket basket) {
        this.basket = basket;
        this.consoleInput = consoleInput;
    }

    /**
     * Adds a product to the basket.
     * 
     * @throws NumberFormatException If the input is not an integer.
     * @return true if the product was successfully added to basket, otherwise false.
     */
    public boolean run() {
        List<Product> allavailableproducts = ProductManager.loadProducts();
        List<Product> inventory = new ArrayList<>();

        for (Product prod: allavailableproducts) {
            if (basket.currentStock(prod) > 0) {
                inventory.add(prod);
            }
        }
        
        if (inventory.isEmpty()) {
            System.out.println("There are no available products. Sorry");
            return false;
        }

        inventory.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        printProductOptions(inventory);

        try {
            int choice = Integer.parseInt(consoleInput.nextLine());

            if (choice == 0) {
                System.out.println("Product selection cancelled.");
                return false;
            }

            int index = choice - 1;
            if (index < 0 || index >= inventory.size()) {
                System.out.println("Invalid Input. Product does not exist");
                return false;
            }

            Product selectedProduct = inventory.get(index);

            System.out.println("Successfully added item to basket");
            return basket.addItem(selectedProduct);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input. Please enter a valid number");
            return false;
        }
    }

    // Formatting for list of product options.
    private void printProductOptions(List<Product> inventory) {
        String line = "=".repeat(75);

        System.out.println("ADDING PRODUCT TO BASKET");
        System.out.println("SELECT A PRODUCT BY INPUTTING THE CORRESPONDING NUMBER (or 0 to cancel)");
        System.out.println(line);
        System.out.printf(
            "%-1s | %-9s | %-30s | %-5s | %-20s%n", 
            "No" , "Category", "Product" , "Price" , "Stock available"
        );
        System.out.println(line);
        
        for (int i = 0; i < inventory.size(); i++) {
        	Product prod = inventory.get(i);
            int stock = basket.currentStock(prod);
            String stockStatus = stock + " available";

        	System.out.format(
                "%2d | %-9s | %-30s | %5.2f | %-20s%n", 
                (i + 1), prod.getProductCategory(), prod.getProductName(),  prod.getPrice(), stockStatus
            );
        }
        System.out.println("");
        System.out.printf("%2d | %-30s%n", 0, "Cancel selection");
    }
}