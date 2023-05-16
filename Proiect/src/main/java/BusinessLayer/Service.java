package BusinessLayer;

import DataAccessLayer.BillRepo;
import DataAccessLayer.ClientRepo;
import DataAccessLayer.OrderRepo;
import DataAccessLayer.ProductRepo;
import Models.Bill;
import Models.Client;
import Models.Order;
import Models.Product;
import java.time.LocalDateTime;

public class Service {
    private static Service instance;

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    private ClientRepo clientRepo;
    private OrderRepo orderRepo;
    private ProductRepo productRepo;
    private BillRepo billRepo;

    private Service() {
        clientRepo = new ClientRepo();
        orderRepo = new OrderRepo();
        productRepo = new ProductRepo();
        billRepo = new BillRepo();
    }

    private static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

    /**
     * Adds a new client to the system.
     *
     * @param idString  the identifier of the client to be added, as a string
     * @param name      the name of the client
     * @param address   the address of the client
     * @param email     the email of the client
     * @param ageString the age of the client, as a string
     * @throws ServiceException if any of the parameters is null or if the identifier is not a number or already exists in the system
     */
    public void addClient(String idString, String name, String address, String email, String ageString) throws ServiceException {
        int id, age;
        if (isEmpty(name) || isEmpty(address) || isEmpty(email) || isEmpty(ageString)) {
            throw new ServiceException("There cannot be null inputs!");
        }
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException ignored) {
            throw new ServiceException("The identifier should be a number!");
        }
        if (clientRepo.findById(id) != null) {
            throw new ServiceException("The identifier shoud be unique!");
        }
        try {
            age = Integer.parseInt(ageString);
        } catch (NumberFormatException ignored) {
            throw new ServiceException("The age should be a number!");
        }
        Client client = new Client(id, name, address, email, age);
        clientRepo.add(client);
    }

    /**
     * Updates the information of an existing client in the system.
     *
     * @param idString  the identifier of the client to be updated, as a string
     * @param name      the new name of the client, or null if it should not be updated
     * @param address   the new address of the client, or null if it should not be updated
     * @param email     the new email of the client, or null if it should not be updated
     * @param ageString the new age of the client, as a string, or null if it should not be updated
     * @throws ServiceException if the identifier is null or not a number, or if no client with the given identifier is found
     */
    public void updateClient(String idString, String name, String address, String email, String ageString) throws ServiceException {
        int id;
        if (isEmpty(idString))
            throw new ServiceException("Client id cannot be null!");
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException ignored) {
            throw new ServiceException("The identifier should be a number!");
        }
        Client client = clientRepo.findById(id);
        if (client == null) {
            throw new ServiceException("No client found with the given identifier!");
        }
        if (!isEmpty(name)) {
            client.setName(name);
        }
        if (!isEmpty(address)) {
            client.setAddress(address);
        }
        if (!isEmpty(email)) {
            client.setEmail(email);
        }
        if (!isEmpty(ageString)) {
            try {
                client.setAge(Integer.parseInt(ageString));
            } catch (NumberFormatException ignored) {
                throw new ServiceException("The age should be a number!");
            }
        }
        clientRepo.update(client);
    }

    /**
     * Deletes a client from the system.
     *
     * @param idString the identifier of the client to be deleted, as a string
     * @throws ServiceException if the identifier is null or not a number, or if no client with the given identifier is found
     */
    public void deleteClient(String idString) throws ServiceException {
        int id;
        if (isEmpty(idString))
            throw new ServiceException("Null input not allowed!");
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException ignored) {
            throw new ServiceException("The identifier should be a number!");
        }
        if (clientRepo.findById(id) == null) {
            throw new ServiceException("No client found with the given identifier!");
        }
        clientRepo.delete(id);
    }

    /**
     * Adds a new product to the system.
     *
     * @param idString       the identifier of the product to be added, as a string
     * @param name           the name of the product
     * @param quantityString the quantity of the product, as a string
     * @param priceString    the price of the product, as a string
     * @throws ServiceException if any of the parameters is null or if the identifier is not a number or already exists in the system
     */
    public void addProduct(String idString, String name, String quantityString, String priceString) throws ServiceException {
        int quantity;
        if (isEmpty(idString) || isEmpty(name) || isEmpty(quantityString) || isEmpty(priceString))
            throw new ServiceException("No null inputs allowed!");
        int id;
        float price;
        if (isEmpty(idString) || isEmpty(name) || isEmpty(quantityString) || isEmpty(priceString)) {
            throw new ServiceException("There cannot be null inputs!");
        }
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            throw new ServiceException("The identifier should be a number!");
        }
        if (productRepo.findById(id) != null) {
            throw new ServiceException("The identifier should be unique!");
        }
        try {
            quantity = Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            throw new ServiceException("The quantity should be a number!");
        }
        try {
            price = Float.parseFloat(priceString);
        } catch (NumberFormatException ignored) {
            throw new ServiceException("The price should be a number!");
        }
        Product product = new Product(id, name, quantity, price);
        productRepo.add(product);
    }

    /**
     * Updates the information of an existing product in the system.
     *
     * @param idString       the identifier of the product to be updated, as a string
     * @param name           the new name of the product, or null if it should not be updated
     * @param quantityString the new quantity of the product, as a string, or null if it should not be updated
     * @param priceString    the new price of the product, as a string, or null if it should not be updated
     * @throws ServiceException if the identifier is null or not a number, or if no product with the given identifier is found
     */
    public void updateProduct(String idString, String name, String quantityString, String priceString) throws ServiceException {
        int id;
        if (isEmpty(idString))
            throw new ServiceException("The product identifier cannot be null!");
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException ignored) {
            throw new ServiceException("The identifier should be a number!");
        }
        Product product = productRepo.findById(id);
        if (product == null) {
            throw new ServiceException("The product is not found in our database!");
        }
        if (!isEmpty(name)) {
            product.setName(name);
        }
        if (!isEmpty(quantityString)) {
            try {
                product.setQuantity(Integer.parseInt(quantityString));
            } catch (NumberFormatException ignored) {
                throw new ServiceException("The quantity should be a number!");
            }
        }
        if (!isEmpty(priceString)) {
            try {
                product.setPrice(Float.parseFloat(priceString));
            } catch (NumberFormatException ignored) {
                throw new ServiceException("The price should be a number!");
            }
        }
        productRepo.update(product);
    }


    /**
     * Deletes a product from the system.
     *
     * @param idString the identifier of the product to be deleted, as a string
     * @throws ServiceException if the identifier is null or not a number, or if no product with the given identifier is found
     */

    public void deleteProduct(String idString) throws ServiceException {
        int id;
        if (isEmpty(idString))
            throw new ServiceException("Null input not allowed!");
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException ignored) {
            throw new ServiceException("The identifier should be a number!");
        }
        if (productRepo.findById(id) == null) {
            throw new ServiceException("The product is not found in our database!");
        }
        productRepo.delete(id);
    }

    /**
     * Adds a new order to the database.
     *
     * @param orderIdString  The ID of the order as a string.
     * @param clientIdString The ID of the client who placed the order as a string.
     * @param productIdString The ID of the product ordered as a string.
     * @param quantityString The quantity of the product ordered as a string.
     * @throws ServiceException If any of the input strings are empty or null, or if the input
     *                           identifiers are not numbers, or if the client or product does not exist
     *                           in the database, or if the order ID is not unique, or if there is insufficient stock for the product ordered.
    */
public void addOrder( String orderIdString, String clientIdString, String productIdString, String quantityString) throws ServiceException {
        int quantity;
        int idO, idC, idP;
        ClientRepo client = new ClientRepo();
        ProductRepo product = new ProductRepo();
        OrderRepo order = new OrderRepo();
        if (isEmpty(orderIdString) || isEmpty(clientIdString) || isEmpty(productIdString) || isEmpty(quantityString)) {
            throw new ServiceException("There cannot be null inputs!");
        }
        try {
            idC = Integer.parseInt(clientIdString);
            idO = Integer.parseInt(orderIdString);
            idP = Integer.parseInt(productIdString);
        } catch (NumberFormatException e) {
            throw new ServiceException("The identifier should be a number!");
        }
        if (client.findById(idC) == null)
            throw new ServiceException("The client is not existent in our database!");
        if (product.findById(idP) == null)
            throw new ServiceException("The product is not existent in our database!");
        if (order.findById(idO) != null) {
            throw new ServiceException("The identifier should be unique!");
        }
        try {
            quantity = Integer.parseInt(quantityString);
        } catch (NumberFormatException ignored) {
            throw new ServiceException("The quantity should be a number!");
        }
        if (product.findById(idP).getQuantity() < quantity)
            throw new ServiceException("Insufficient stock!");
        Order newOrder = new Order(idO, idC,idP, quantity);
        orderRepo.add(newOrder);
    }

    /**
     * Deletes an order from the database by ID.
     *
     * @param idString The ID of the order to delete as a string.
     * @throws ServiceException If the input string is empty or null, or if the input identifier is not
     *                           a number, or if the order does not exist in the database.
     */
    public void deleteOrder(String idString) throws ServiceException {
        int id;
        if (isEmpty(idString))
            throw new ServiceException("Null input not allowed!");
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException ignored) {
            throw new ServiceException("The identifier should be a number!");
        }
        if (orderRepo.findById(id) == null) {
            throw new ServiceException("No such order is found in our database!");
        }
        orderRepo.delete(id);
    }

    /**
     * Adds a new bill to the database.
     *
     * @param id       The ID of the bill.
     * @param orderId  The ID of the order that the bill corresponds to.
     * @param clientId The ID of the client who placed the order.
     * @param total    The total amount of the bill.
     * @param date     The date and time when the bill was created.
     */
    public void addBill(int id, int orderId, int clientId, double total, LocalDateTime date) {
        Bill bill = new Bill(id, orderId, clientId, total, date);
        billRepo.add(bill);
    }
}
