package app;

import java.time.LocalDate;
import java.util.*;

public class CreditCardPayment implements PaymentMethod {
	private final Scanner consoleInput = new Scanner(System.in);
	
    @Override
    public Receipt processPayment(double total, Address address) {		
	    System.out.println("Enter 6-digit card number: ");
	    int cardnumber = Integer.parseInt(consoleInput.nextLine().trim());
	    
	    // CVV = 3-digit security number
	    System.out.println("Enter 3-digit CVV: ");
	    int cvv = Integer.parseInt(consoleInput.nextLine().trim());
		
	    if (!String.valueOf(cardnumber).matches("\\d{6}") || !String.valueOf(cvv).matches("\\d{3}")) {
	    	return null;
	    }
		
	    return new Receipt(
	    	total, 
	    	address, 
	   		"Credit Card (" + cardnumber +")", 
			LocalDate.now(), 
	    	new ArrayList<>()
	    );
    }
}