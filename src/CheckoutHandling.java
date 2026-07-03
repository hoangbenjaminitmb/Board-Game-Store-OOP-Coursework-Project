package app;

import java.time.LocalDate;
import java.util.*;

public class CheckoutHandling {

    /**
     * Processes a receipt for a customer's purchase. Returns a Receipt containing transaction details.
     * 
     * @param user User that makes the purchase, which also includes their address..
     * @param items List of products being purchased and printed in the receipt..
     * @param paymentMethod Method used to process the payment of all products in basket.
     * @return Receipt containing total cost, user's address, payment used, etc. Null if payment fails.
     */
    public Receipt processReceipt(User user, List<Product> items, PaymentMethod paymentMethod) {
        double total = 0;
        for (Product item : items) {
            total += item.getPrice();
        }
        Receipt method = paymentMethod.processPayment(total, user.getAddress());
        
        if (method == null) {
        	return null;
        }
        updateStock(items);

        return new Receipt(
            total, 
            user.getAddress(), 
            method.paymentMethodUsed(), 
            LocalDate.now(), 
            items
        );
    }

    /**
     * Deducts the amount of products in stock based on how many the user has bought.
     * 
     * @param purchasedItems the list of products purchased by the user.
     */
    private static void updateStock(List<Product> purchasedItems) {
        List<Product> currentStock = ProductManager.loadProducts();

        for (Product purchased : purchasedItems) {
            for (Product product : currentStock) {
                if (product.getProductId() == purchased.getProductId()) {
                    product.setQuantityInStock(product.getQuantityInStock() - 1);
                }
            }
        }
        ProductManager.saveProducts(currentStock);
    }
}