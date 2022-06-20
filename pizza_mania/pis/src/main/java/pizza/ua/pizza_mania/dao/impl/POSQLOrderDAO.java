package pizza.ua.pizza_mania.dao.impl;

import pizza.ua.pizza_mania.connection.PSQLConnector;
import pizza.ua.pizza_mania.dao.OrderDAO;
import pizza.ua.pizza_mania.entities.Order;
import pizza.ua.pizza_mania.entities.OrderCheck;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class POSQLOrderDAO implements OrderDAO {

    Operations operations = new Operations();
    private static final String table = "order";
    private static final String[] columns = {"order_id","order_number","pizza_id","amount","total_price", "order_status_id"};

    @Override
    public Order findById(int id) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.SELECT(table) + " WHERE "+columns[0] + "="+id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Order order = null;
        if(resultSet.next()){
            order = new Order(resultSet.getInt(columns[0]),resultSet.getInt(columns[1]), resultSet.getInt(columns[2])
                    ,resultSet.getInt(columns[3]), resultSet.getInt(columns[4]),resultSet.getInt(columns[5]));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return order;
    }

    @Override
    public List<Order> findAll() throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.SELECT(table));
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Order> order = new ArrayList<>();
        while (resultSet.next()){
            order.add(new Order(resultSet.getInt(columns[0]),resultSet.getInt(columns[1]), resultSet.getInt(columns[2])
                    ,resultSet.getInt(columns[3]), resultSet.getInt(columns[4]),resultSet.getInt(columns[5])));
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return order;
    }

    @Override
    public boolean addOrder(Order order) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.INSERT(table, columns), Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, order.getOrderNumber());
        preparedStatement.setInt(2, order.getPizzaId());
        preparedStatement.setInt(3, order.getAmount());
        preparedStatement.setInt(4, order.getTotal_price());
        preparedStatement.setInt(5, order.getOrderStatusId());

        if(preparedStatement.executeUpdate() == 1){
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                order.setOrderId(resultSet.getInt(columns[0]));
            }
            resultSet.close();
        }
        preparedStatement.close();
        connection.close();
        return order.getOrderId()!=null;
    }

    @Override
    public void updateOrder(Order order) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.UPDATE(table, columns));

        preparedStatement.setInt(1, order.getOrderNumber());
        preparedStatement.setInt(2, order.getPizzaId());
        preparedStatement.setInt(3, order.getAmount());
        preparedStatement.setInt(4, order.getTotal_price());
        preparedStatement.setInt(5, order.getOrderStatusId());
        preparedStatement.setInt(6, order.getOrderId());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

    }

    @Override
    public void deleteOrder(Order order) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.DELETE(table, columns[0]));

        preparedStatement.setInt(1, order.getOrderId());
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }
}
