package DataAccessLayer;

import Models.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class BillRepo extends AbstractDAO<Bill> {

    /**
     * Retrieves a list of all bills from the database.
     *
     * @return A list of Bill objects representing all the bills in the database.
     */
    @Override
    public List<Bill> findAll() {
        List<Bill> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM bill");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int orderId = resultSet.getInt("orderId");
                int clientId = resultSet.getInt("clientId");
                double total = resultSet.getFloat("total");
                LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();
                result.add(new Bill(id, orderId, clientId, total, date));
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "BillDAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        }
        return result;
    }

    /**
     * Inserts a Bill object into the database.
     *
     * @param bill The Bill object to be inserted into the database.
     */
    public void add(Bill bill) {
        super.insert(bill);
    }

    /**
     * Retrieves a Bill object from the database with the specified id.
     *
     * @param id The id of the Bill object to be retrieved.
     * @return A Bill object representing the bill with the specified id, or null if the bill is not found.
     */
    @Override
    public Bill findById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM bill WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int orderId = resultSet.getInt("orderId");
                int clientId = resultSet.getInt("clientId");
                double total = resultSet.getFloat("total");
                LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();
                return new Bill(id, orderId, clientId, total, date);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "BillDAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Retrieves the highest id from the bill table in the database.
     *
     * @return The highest id from the bill table.
     */
    public int getBiggestId() {
        List<Bill> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = 0;
        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT max(id) AS biggestId FROM bill");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("biggestId");
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "BillDAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        }
        return id;
    }



}

