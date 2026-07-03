package app;

import java.util.*;

public class CustomerCLI {
    private final Scanner consoleInput;
    private final AddToBasket addToBasket;
    private final ViewBasket viewBasket;
    private final Basket basket;
    private final Checkout checkout;

    public CustomerCLI(Scanner consoleInput, User user) {
        this.consoleInput = consoleInput;
        this.basket = new Basket();
        this.checkout = new Checkout(user, basket);
        this.addToBasket = new AddToBasket(consoleInput, basket);
        this.viewBasket = new ViewBasket(basket);
	}

    public void run() {
        System.out.println("CUSTOMER VIEW");
        while (true) { 
            printCustomerMenu();
            try {
                switch (Integer.parseInt(consoleInput.nextLine().trim())) {
                    case 1 -> ProductDisplay.displayProducts(false, null);
                    case 2 -> addToBasket.run();
                    case 3 -> viewBasket.run();
                    case 4 -> checkout.run();
                    case 5 -> {
                    	if (basket.emptyBasket()) {
                    		System.out.println("Basket is already empty");
                    	} else {
                    		basket.clearBasket();
                    		System.out.println("All items in basket cleared");
                    	}
                    }
                    case 6 -> ProductSearch.Search(new SearchById());
                    case 7 -> ProductSearch.Search(new FilterByCompatibility());
                    case 0 -> {
                        basket.clearBasket();
                        System.out.println("Log out successful");
                        return;
                    }
                    default -> {System.out.println("Invalid selection");}
                }
            } catch (NumberFormatException e) {
            	System.out.println("Invalid selection");
            } 
        }
    }

    private static void printCustomerMenu() {
        System.out.println("PLEASE SELECT ACTION BY INPUTTING THE CORRESPONDING NUMBER (or 0 for logout)");
        System.out.println("1) View all products");
        System.out.println("2) Add product to shopping basket");
        System.out.println("3) View contents of shopping basket");
        System.out.println("4) Purchase items in the basket");
        System.out.println("5) Cancel shopping basket");
        System.out.println("6) Lookup with product ID");
        System.out.println("7) Search/filter based on compatilbility");

        System.out.println("0) Log out");
    }
}