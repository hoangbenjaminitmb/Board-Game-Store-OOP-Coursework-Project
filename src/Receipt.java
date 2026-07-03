package app;

import java.time.LocalDate;
import java.util.*;

public class Receipt {
    private final double total;
    private final Address address;
    private final String paymentmethod;
    private final LocalDate date;
    private final List<Product> items;
    
    /**
     * Prints out the receipt containing all relevant details about the purchase made by user. 
     * 
     * @param total The total amount paid for products bought by the user
     * @param address The full address associated with the user, including house number, postcode, and city
     * @param paymentmethod The payment method selected, including PayPal or Credit Card
     * @param date The date that the purchase was made
     * @param items The list of items bought by the user
     */
    public Receipt(
        double total, 
        Address address, 
        String paymentmethod, 
        LocalDate date,
        List<Product> items
    ) {
        this.total = total;
        this.address = address;
        this.paymentmethod = paymentmethod;
        this.date = date;
        this.items = items;
    }
    public String paymentMethodUsed() {
        return this.paymentmethod;
    }

    public void print() {
        String divider = "=".repeat(70);

        System.out.println(divider);
        System.out.println("RECEIPT");
        System.out.println(divider);
        System.out.println("Payment Method: " + paymentmethod);
        System.out.println("Date: " + date);
        System.out.println("\nItems Purchased:");
        System.out.println(divider);

        System.out.printf(
            "%-30s | %-10s | %-10s | %-10s%n" , 
            "Product" , "Price" , "Quantity" , "Subtotal"
        );
        
        System.out.println(divider);
        
        itemsBought();

        System.out.println("\nShipping address:");
        System.out.println("House number: " + address.getHouse());
        System.out.println("Postcode: " + address.getPostcode());
        System.out.println("City: " + address.getCity());

        System.out.println(divider);
        System.out.printf("TOTAL PAID: %6.2f%n" , total);
        System.out.println("");
    }

    private void itemsBought() {
        for (int i = 0; i < items.size(); i++) {
            Product prod = items.get(i);
            int quantity = 1;

            // Quantity of the same item purchased
            while (i + 1 < items.size() && items.get(i + 1).getProductName().equals(prod.getProductName())) {
                quantity++;
                i++;
            }
            System.out.printf(
                // product name | price | quantity | subtotal
                "%-30s | %-10.2f | %-10d | %-10.2f%n", 
                prod.getProductName().trim() , prod.getPrice() , quantity , prod.getPrice() * quantity
            );
        }
    }
}