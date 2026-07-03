package app;

import java.util.*;

public class Basket {
    private final List<Product> items = new ArrayList<>();

    // Calculate total cost of total items in basket
    public double calculateTotalCost() {
        double total = 0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }

    // Count how many items of same product in basket
    public int countItems(Product product) {
        int count = 0;
        for (Product item : items) {
            if (item.getProductId() == product.getProductId()) {
                count++;
            }
        }
        return count;
    }

    // Current stock of a product. Decreases if same product is in basket.
    public int currentStock(Product product) {
        return product.getQuantityInStock() - countItems(product);
    }

    // Boolean to add item to basket.
    public boolean addItem(Product product) {
        return items.add(product);
    }

    // Boolean to check if basket is empty.
    public boolean emptyBasket() {
        return items.isEmpty();
    }

    // List of items in basket
    public List<Product> fetchItems() {
        return new ArrayList<>(items);
    }

    // Clear all items in basket
    public void clearBasket() {
        items.clear();
    }
}