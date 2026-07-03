package app;

public abstract class Product {
    private final int id;
    private final ProductCategory productCategory;
    private final String productName;
    private final double price;
    private int stockQuantity;
    private final double purchaseCost;

    public Product(
        int productId,
        ProductCategory ProductCategory,
        String productName,
        double purchaseCost,
        int quantityInStock,
        double price
    ) {
        this.id = productId;
        this.productCategory = ProductCategory;
        this.productName = productName;
        this.purchaseCost = purchaseCost;
        this.stockQuantity = quantityInStock;
        this.price = price;
    }

    public int getProductId() {return id;}
    public ProductCategory getProductCategory() {return productCategory; }
    public String getProductName() {return productName;}
    public double getPurchaseCost() {return purchaseCost;}
    public int getQuantityInStock() {return stockQuantity;}
    public double getPrice() {return price;}

    public void setQuantityInStock(int quantityInStock) {
        this.stockQuantity = quantityInStock;
    }

    @Override
    public abstract String toString();
}
