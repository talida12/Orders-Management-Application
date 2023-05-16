package Models;

import DataAccessLayer.OrderRepo;
import DataAccessLayer.ProductRepo;

/**
 * Represents an order placed by a client for a particular product.
 */
public class Order {

    /** The unique identifier for this order. */
    private int id;

    /** The ID of the client who placed this order. */
    private int clientId;

    /** The ID of the product being ordered. */
    private int productId;

    /** The number of units of the product being ordered. */
    private int nrProducts;

    /** Creates a new order with default values. */
    public Order() {}

    /**
     * Creates a new order with the specified values.
     * @param id the unique identifier for this order
     * @param clientId the ID of the client who placed this order
     * @param productId the ID of the product being ordered
     * @param nrProducts the number of units of the product being ordered
     */
    public Order(int id, int clientId, int productId, int nrProducts) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.nrProducts = nrProducts;
    }

    /**
     * Returns the unique identifier for this order.
     * @return the unique identifier for this order
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the ID of the client who placed this order.
     * @return the ID of the client who placed this order
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Returns the ID of the product being ordered.
     * @return the ID of the product being ordered
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Returns the number of units of the product being ordered.
     * @return the number of units of the product being ordered
     */
    public int getNrProducts() {
        return nrProducts;
    }

    /**
     * Sets the unique identifier for this order.
     * @param id the new unique identifier for this order
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the total cost of this order, based on the price of the ordered product and the number of units ordered.
     * @return the total cost of this order
     */
    public double getOrderTotal() {
        ProductRepo product = new ProductRepo();
        return product.findProductById(productId).getPrice() * nrProducts;
    }

    /**
     * Sets the ID of the client who placed this order.
     * @param clientId the new ID of the client who placed this order
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * Sets the ID of the product being ordered.
     * @param productId the new ID of the product being ordered
     */
    public void setProductId(int productId){
        this.productId = productId;
    }

    /**
     * Sets the number of units of the product being ordered.
     * @param nr the new number of units of the product being ordered
     */
    public void setNrProducts(int nr) {
        this.nrProducts = nr;
    }

}
