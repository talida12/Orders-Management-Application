package DataAccessLayer;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM `");
        sb.append(type.getSimpleName());
        sb.append("` WHERE " + field + " = ?");
        return sb.toString();
    }

    public List<T> findAll() {
        List<T> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM `" + type.getSimpleName() + "`";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            list = createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return list;
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            var results = createObjects(resultSet);
            if (results.isEmpty()) {
                return null;
            }
            return results.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                System.out.println(instance);
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                System.out.println(instance);
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        System.out.println(list);
        return list;
    }

    public void insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String className = type.getSimpleName();
            String tableName = className.toLowerCase();
            Field[] fields = type.getDeclaredFields();
            StringBuilder sb = new StringBuilder("INSERT INTO `" + tableName + "` (");
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                sb.append(field.getName());
                if (i < fields.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append(") VALUES (");
            for (int i = 0; i < fields.length; i++) {
                sb.append("?");
                if (i < fields.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append(")");
            String sql = sb.toString();
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                Object value = field.get(t);
                statement.setObject(i + 1, value);
            }
            System.out.println(statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public void update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String className = type.getSimpleName();
            String tableName = className.toLowerCase();
            Field[] fields = type.getDeclaredFields();
            StringBuilder sb = new StringBuilder("UPDATE `" + tableName + "` SET ");
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                String fieldName = field.getName();
                if (!fieldName.equals("id")) {
                    sb.append(fieldName).append(" = ?");
                    if (i < fields.length - 1) {
                        sb.append(", ");
                    }
                }
            }
            sb.append(" WHERE id = ?");
            String sql = sb.toString();
            statement = connection.prepareStatement(sql);
            int index = 1;
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if (!fieldName.equals("id")) {
                    Object value = field.get(t);
                    statement.setObject(index, value);
                    index++;
                }
            }
            Field idField = type.getDeclaredField("id");
            idField.setAccessible(true);
            Object idValue = idField.get(t);
            statement.setObject(index, idValue);
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException | NoSuchFieldException ex) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + ex.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String className = type.getSimpleName();
            String tableName = className.toLowerCase();
            statement = connection.prepareStatement("DELETE FROM `" + tableName + "` WHERE id = ?");
            Field idField = type.getDeclaredField("id");
            idField.setAccessible(true);
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException | NoSuchFieldException ex) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + ex.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
