package app;

import java.util.*;

public class ProductSearch {
    public static void Search(SearchMethod Method) {
        List<Product> inventory = ProductManager.loadProducts();
        List<Product> results = Method.filter(inventory);
        displayResult(results);
    }
    
    private static void displayResult(List<Product> results) {
        if (results.isEmpty()) {
            System.out.println("No matching available products found.");
        } else {
            ProductDisplay.displayProducts(false, results);
        }
    }
}