package DataAccessLayer;

import Models.Order;
import Models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class OrderRepo extends AbstractDAO<Order> {
    /**
     * Retrieves all orders from the database.
     * @return a list of all orders in the "order" table.
     */
    public List<Order> findAll() {
        List<Order> list = super.findAll();
        return list;
    }

    /**
     * Adds a new order to the database.
     * @param order the order to be added to the "order" table.
     */
    public void add(Order order) {
        super.insert(order);
    }

    /**
     * Deletes an order from the database by its ID.
     * @param orderId the ID of the order to be deleted from the "order" table.
     */
    public void delete(int orderId) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "DELETE FROM `order`"  + " WHERE id = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.log(Level.WARNING, Product.class.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

}
