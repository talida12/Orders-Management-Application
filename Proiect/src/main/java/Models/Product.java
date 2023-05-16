package Models;

/**
 * Represents a product that can be sold in a store.
 */
public class Product {

    /** The unique identifier for this product. */
    private int id;

    /** The name of this product. */
    private String name;

    /** The number of units of this product available for sale. */
    private int quantity;

    /** The price per unit of this product. */
    private double price;

    /** Creates a new product with default values. */
    public Product() {}

    /**
     * Creates a new product with the specified values.
     * @param id the unique identifier for this product
     * @param productName the name of this product
     * @param quantity the number of units of this product available for sale
     * @param price the price per unit of this product
     */
    public Product(int id, String productName, int quantity, double price) {
        this.id = id;
        this.name = productName;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Returns the name of this product.
     * @return the name of this product
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this product.
     * @param name the new name of this product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the number of units of this product available for sale.
     * @return the number of units of this product available for sale
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the number of units of this product available for sale.
     * @param quantity the new number of units of this product available for sale
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the unique identifier for this product.
     * @return the unique identifier for this product
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for this product.
     * @param id the new unique identifier for this product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the price per unit of this product.
     * @return the price per unit of this product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price per unit of this product.
     * @param price the new price per unit of this product
     */
    public void setPrice(double price) {
        this.price = price;
    }

}
