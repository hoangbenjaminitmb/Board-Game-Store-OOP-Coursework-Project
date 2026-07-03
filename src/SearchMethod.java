package app;

import java.util.List;

public interface SearchMethod {
    /**
     * Used to search and filter products by their product ID or compatibility
     * 
     * @param inventory The products loaded from the inventory.
     */
    List<Product> filter(List<Product> inventory);
}