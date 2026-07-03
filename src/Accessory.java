package app;

public class Accessory extends Product {
    private final String type;
    private final String compatibility;
    
    public String getProductType() {return type;}
    public String getCompatibility() {return compatibility;}

    public Accessory(
        int id,
        String type,
        String name,
        double price,
        int qty,
        double cost,
        String compatibility
    ) {
        super(id, ProductCategory.ACCESSORY, name, cost, qty, price);
        this.type = type;
        this.compatibility = compatibility;
    }
    
    @Override
    public String toString() {
    	String compinfo = compatibility + " compatible";
        return String.format(
            "%-4s | %-9s | %-13s | %-29s | %-8.2f | %-8s | " + (ProductDisplay.isAdmin() ? "%-13.2f | ":"")  + "%-20s%n", 
            getProductId(), 
            getProductCategory(), 
            type, 
            getProductName(), 
            getPrice(), 
            getQuantityInStock(), 
            (ProductDisplay.isAdmin() ? getPurchaseCost() : compinfo), 
            compinfo
        );
    }
}