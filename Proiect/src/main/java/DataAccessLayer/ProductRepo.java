package DataAccessLayer;

import Models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class ProductRepo extends AbstractDAO<Product> {
    /**
     * Returns a list of all products in the database.
     * @return a list of all products in the database
     */
    public List<Product> findAll() {
        List<Product> list = super.findAll();
        return list;
    }

    /**
     * Inserts a new product into the database.
     * @param product the product to be inserted
     */
    public void add(Product product) {
        super.insert(product);
    }

    /**
     * Updates an existing product in the database.
     * @param product the product to be updated
     */
    public void update(Product product) {
        super.update(product);
    }

    /**
     * Deletes a product with the given product ID from the database.
     * @param productId the ID of the product to be deleted
     */
    public void delete(int productId) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "DELETE FROM product" + " WHERE id = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.log(Level.WARNING, Product.class.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Returns a product with the given product ID from the database.
     * @param productId the ID of the product to be returned
     * @return the product with the given ID
     */
    public Product findProductById(int productId) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "SELECT * FROM product" + " WHERE id = ?";
        Product p = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int nr = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                p = new Product(id, name, nr, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return p;
    }

    /**
     * Updates the quantity of a product with the given product ID in the database.
     * @param productId the ID of the product to be updated
     * @param quantity  the quantity of the product that was retrieved
     */
    public void updateQuantity(int productId, int quantity) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "UPDATE product SET quantity = ?" + " WHERE id = ?";
        Product p = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, findProductById(productId).getQuantity() - quantity);
            statement.setInt(2, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
        }
    }
}
