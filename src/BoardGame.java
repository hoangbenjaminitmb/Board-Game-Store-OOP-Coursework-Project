package app;

public class BoardGame extends Product {
    private final String type;
    private final String playerInfo;
    
    public String getProductType() {return type;}
    public String getPlayerInfo() {return playerInfo;}
    
    public BoardGame(
        int id,
        String type,
        String name,
        double price,
        int qty,
        double cost,
        String playerInfo
    ) {
        super(id, ProductCategory.BOARDGAME, name, cost, qty, price);
        this.type = type;
        this.playerInfo = playerInfo;
    }

    @Override
    public String toString() {
    	String maxplayers = playerInfo + "-player game";
        return String.format(
            "%-4s | %-9s | %-13s | %-29s | %-8.2f | %-8s | " + (ProductDisplay.isAdmin() ? "%-13.2f | ":"")  + "%-20s%n", 
            getProductId(), 
            getProductCategory(), 
            type, 
            getProductName(), 
            getPrice(), 
            getQuantityInStock(), 
            (ProductDisplay.isAdmin() ? getPurchaseCost() : maxplayers), 
            maxplayers
        );
    }
}