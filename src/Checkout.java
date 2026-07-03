package app;

import java.util.Scanner;

public class Checkout {
    private final Scanner consoleInput;
    private final CheckoutHandling checkoutHandling;
    private final Basket basket;
    private final User user;
    
    public Checkout(User user, Basket basket) {
        this.consoleInput = new Scanner(System.in);
        this.checkoutHandling = new CheckoutHandling();
        this.basket = basket;
        this.user = user;
    }

    public void run() {
        if (basket.emptyBasket()) {
            System.out.println("Your basket is empty");
            return;
        }

        PaymentMethod paymentMethod = selectPaymentMethod();
        if (paymentMethod == null) {
            System.out.println("Payment cancelled");
            return;
        }

        Receipt finalReceipt = checkoutHandling.processReceipt(user, basket.fetchItems(), paymentMethod);
        if (finalReceipt == null) {
            System.out.println("Payment failed. Details not recognised");
            return;
        }

        System.out.println("Transaction complete");
        finalReceipt.print();
        basket.clearBasket();
    }

    /**
     * Prompts the user to select a payment method, including PayPal or Credit Card payment.
      * 
      * @throws NumberFormatException if the user does not enter an integer when selecting payment method.
      * @return a payment method selected by the user. Otherwise null if the user has chosen to cancel the payment.
     */
    private PaymentMethod selectPaymentMethod() {
        System.out.println("\nCHECKOUT");
        System.out.println("SELECT YOUR PAYMENT METHOD: ");
        System.out.println("1) Credit card");
        System.out.println("2) PayPal");
        System.out.println("0) Cancel payment");
        while (true) {
            try {
                switch (Integer.parseInt(consoleInput.nextLine().trim())) {
                    case 1 -> {return new CreditCardPayment();}
                    case 2 -> {return new PayPalPayment();}
                    case 0 -> {return null;}
                    default -> System.out.println("Payment failed.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Payment failed.");
                return null;
            }
        }
    }
}