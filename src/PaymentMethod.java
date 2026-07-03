package app;

public interface PaymentMethod{
    /**
     * Processes a payment for the specified amount
     * 
     * @param total The total amount to be paid
     * @param address The billing address associated with the transaction
     * @return A receipt confirming the processed payment
     */
    Receipt processPayment(double total, Address address);
}