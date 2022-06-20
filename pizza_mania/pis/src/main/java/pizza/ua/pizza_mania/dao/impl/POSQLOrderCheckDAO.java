package pizza.ua.pizza_mania.dao.impl;

import pizza.ua.pizza_mania.connection.PSQLConnector;
import pizza.ua.pizza_mania.dao.OrderCheckDAO;
import pizza.ua.pizza_mania.entities.OrderCheck;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class POSQLOrderCheckDAO implements OrderCheckDAO {

    Operations operations = new Operations();
    private static final String table = "order_check";
    private static final String[] columns = {"order_check_id","check_user_id","check_order_number","order_data","order_time", "total_user_check"};

    @Override
    public OrderCheck findById(int id) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.SELECT(table) + " WHERE "+columns[0] + "="+id);
        ResultSet resultSet = preparedStatement.executeQuery();
        OrderCheck orderCheck = null;
        if(resultSet.next()){
            orderCheck = new OrderCheck(resultSet.getInt(columns[0]),resultSet.getInt(columns[1]), resultSet.getInt(columns[2])
                    ,resultSet.getDate(columns[3]), resultSet.getTime(columns[4]),resultSet.getInt(columns[5]));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return orderCheck;
    }

    @Override
    public List<OrderCheck> findAll() throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.SELECT(table));
        ResultSet resultSet = preparedStatement.executeQuery();

        List<OrderCheck> orderChecks = new ArrayList<>();
        while (resultSet.next()){
            orderChecks.add(new OrderCheck(resultSet.getInt(columns[0]),resultSet.getInt(columns[1]), resultSet.getInt(columns[2])
                    ,resultSet.getDate(columns[3]), resultSet.getTime(columns[4]),resultSet.getInt(columns[5])));
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return orderChecks;
    }

    @Override
    public boolean addOrderCheck(OrderCheck orderCheck) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.INSERT(table, columns), Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, orderCheck.getUser_id());
        preparedStatement.setInt(2, orderCheck.getOrderNumber());
        preparedStatement.setDate(3, orderCheck.getOrderDate());
        preparedStatement.setTime(4, orderCheck.getOrderTime());
        preparedStatement.setInt(5, orderCheck.getTotalUserCheck());

        if(preparedStatement.executeUpdate() == 1){
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                orderCheck.setOrderCheckId(resultSet.getInt(columns[0]));
            }
            resultSet.close();
        }
        preparedStatement.close();
        connection.close();
        return orderCheck.getOrderCheckId()!=null;
    }

    @Override
    public void updateOrderCheck(OrderCheck orderCheck) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.UPDATE(table, columns));

        preparedStatement.setInt(1, orderCheck.getUser_id());
        preparedStatement.setInt(2, orderCheck.getOrderNumber());
        preparedStatement.setDate(3, orderCheck.getOrderDate());
        preparedStatement.setTime(4, orderCheck.getOrderTime());
        preparedStatement.setInt(5, orderCheck.getTotalUserCheck());
        preparedStatement.setInt(6, orderCheck.getOrderCheckId());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

    }

    @Override
    public void deleteOrderCheck(OrderCheck orderCheck) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.DELETE(table, columns[0]));

        preparedStatement.setInt(1, orderCheck.getOrderCheckId());
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

    }
}
