package app;

import java.util.*;

public class ViewBasket {
    private final Basket basket;

    public ViewBasket(Basket basket) {
        this.basket = basket;
    }
    
    public void run() {
        if (basket.emptyBasket()) {
            System.out.println("Your basket is currently empty");
            return;
        }

        System.out.println("YOUR BASKET");
        System.out.println("=".repeat(70));
        System.out.printf(
            "%-30s | %-10s | %-10s | %-10s%n" , 
            "Product" , "Price" , "Quantity" , "Subtotal"
        );

        System.out.println("=".repeat(70));
        printBasket();
        System.out.println("=".repeat(70) + "\n");
        System.out.printf("Total: %.2f%n%n", basket.calculateTotalCost());
    }

    private void printBasket() {
        List<Product> itemsInBasket = basket.fetchItems();

        itemsInBasket.sort((p1, p2) -> p1.getProductName().compareTo(p2.getProductName())); 

        for (int i = 0; i < itemsInBasket.size(); i++) {
            Product prod = itemsInBasket.get(i);
            int quantity = 1;
            
            // Quantity for same item in the user's basket.
            while (i + 1 < itemsInBasket.size() && itemsInBasket.get(i + 1).getProductName().equals(prod.getProductName())) {
                quantity++;
                i++;
            }
            
            System.out.printf(
                "%-30s | %-10.2f | %-10d | %-10.2f%n",
                prod.getProductName().trim() , prod.getPrice() , quantity , prod.getPrice() * quantity
            );
        }
    }
}