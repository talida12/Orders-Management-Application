package DataAccessLayer;
import Models.Client;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.List;

/**

 This class represents the Data Access Object for the Client model.
 It extends the AbstractDAO class and provides methods for accessing the Client database table.
 */


public class ClientRepo extends AbstractDAO<Client> {
    /**
     * Retrieves all the clients from the database.
     *
     * @return a list of all the clients
     */
    public List<Client> findAll() {
        List<Client> list = super.findAll();
        return list;
    }

    /**
     * Adds a new client to the database.
     *
     * @param client the client to be added
     */
    public void add(Client client) {
        super.insert(client);
    }

    /**
     * Updates an existing client in the database.
     *
     * @param client the client to be updated
     */
    public void update(Client client) {
        super.update(client);
    }

    /**
     * Deletes a client from the database by ID.
     *
     * @param clientId the ID of the client to be deleted
     */
    public void delete(int clientId) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "DELETE FROM client" + " WHERE id = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, clientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.log(Level.WARNING, Client.class.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Retrieves a client from the database by ID.
     *
     * @param clientId the ID of the client to be retrieved
     * @return the client object if it exists, or null if not
     */
    public Client findClientById(int clientId) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "SELECT * FROM client" + " WHERE id = ?";
        Client c = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                c = new Client(id, name, address, email, age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return c;
    }

}

