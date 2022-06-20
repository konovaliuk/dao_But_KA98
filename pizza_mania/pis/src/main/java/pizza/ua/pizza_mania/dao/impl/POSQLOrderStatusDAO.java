package pizza.ua.pizza_mania.dao.impl;

import pizza.ua.pizza_mania.connection.PSQLConnector;
import pizza.ua.pizza_mania.dao.OrderStatusDAO;
import pizza.ua.pizza_mania.entities.OrderStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class POSQLOrderStatusDAO implements OrderStatusDAO {

    Operations operations = new Operations();
    private static final String table = "order_status";
    private static final String[] columns = {"order_status_id","status_description"};

    @Override
    public OrderStatus findById(int id) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.SELECT(table) + " WHERE "+columns[0] + "="+id);
        ResultSet resultSet = preparedStatement.executeQuery();
        OrderStatus orderStatus = null;
        if(resultSet.next()){
            orderStatus = new OrderStatus(resultSet.getInt(columns[0]),resultSet.getString(columns[1]));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return orderStatus;
    }

    @Override
    public List<OrderStatus> findAll() throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.SELECT(table));
        ResultSet resultSet = preparedStatement.executeQuery();

        List<OrderStatus> orderStatuses = new ArrayList<>();
        while (resultSet.next()){
            orderStatuses.add(new OrderStatus(resultSet.getInt(columns[0]),resultSet.getString(columns[1])));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return orderStatuses;
    }

    @Override
    public boolean addOrderStatus(OrderStatus orderStatus) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.INSERT(table, columns), Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, orderStatus.getStatusDescription());

        if(preparedStatement.executeUpdate() == 1){
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                orderStatus.setOrderId(resultSet.getInt(columns[0]));
            }
            resultSet.close();
        }
        preparedStatement.close();
        connection.close();
        return orderStatus.getOrderId()!=null;
    }

    @Override
    public void updateOrderStatus(OrderStatus orderStatus) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.UPDATE(table, columns));

        preparedStatement.setString(1, orderStatus.getStatusDescription());
        preparedStatement.setInt(2, orderStatus.getOrderId());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

    }

    @Override
    public void deleteOrderStatus(OrderStatus orderStatus) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.DELETE(table, columns[0]));

        preparedStatement.setInt(1, orderStatus.getOrderId());
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }
}
