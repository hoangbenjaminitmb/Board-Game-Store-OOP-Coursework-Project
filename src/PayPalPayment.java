package app;

import java.time.LocalDate;
import java.util.*;

public class PayPalPayment implements PaymentMethod {
	private final Scanner consoleInput = new Scanner(System.in);
	
    @Override
    public Receipt processPayment(double total, Address address) {		
	    System.out.print("Enter your Paypal Email: ");
	    String paypalemail = consoleInput.nextLine().trim();
	
	    if (paypalemail.isBlank() || !paypalemail.contains("@") || !paypalemail.contains(".")) {
		    return null;
	    }
	    
	    return new Receipt(
		    total, 
		    address, 
		    "PayPal (" + paypalemail +")", 
		    LocalDate.now(), 
		    new ArrayList<>()
	    );
    }
}