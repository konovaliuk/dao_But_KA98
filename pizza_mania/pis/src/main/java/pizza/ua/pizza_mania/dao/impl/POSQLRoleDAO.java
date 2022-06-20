package pizza.ua.pizza_mania.dao.impl;

import pizza.ua.pizza_mania.connection.PSQLConnector;
import pizza.ua.pizza_mania.dao.UserRoleDAO;
import pizza.ua.pizza_mania.entities.OrderStatus;
import pizza.ua.pizza_mania.entities.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class POSQLRoleDAO implements UserRoleDAO {

    Operations operations = new Operations();
    private static final String table = "user_role";
    private static final String[] columns = {"user_role_id","role_name"};

    @Override
    public UserRole findById(int id) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.SELECT(table) + " WHERE "+columns[0] + "="+id);
        ResultSet resultSet = preparedStatement.executeQuery();
        UserRole userRole = null;
        if(resultSet.next()){
            userRole = new UserRole(resultSet.getInt(columns[0]),resultSet.getString(columns[1]));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return userRole;
    }

    @Override
    public List<UserRole> findAll() throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.SELECT(table));
        ResultSet resultSet = preparedStatement.executeQuery();

        List<UserRole> userRoles = new ArrayList<>();
        while (resultSet.next()){
            userRoles.add(new UserRole(resultSet.getInt(columns[0]),resultSet.getString(columns[1])));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return userRoles;
    }

    @Override
    public boolean addUserRole(UserRole userRole) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.INSERT(table, columns), Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, userRole.getRoleName());

        if(preparedStatement.executeUpdate() == 1){
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                userRole.setUserRoleId(resultSet.getInt(columns[0]));
            }
            resultSet.close();
        }
        preparedStatement.close();
        connection.close();
        return userRole.getUserRoleId()!=null;
    }

    @Override
    public void updateUserRole(UserRole userRole) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.UPDATE(table, columns));

        preparedStatement.setString(1, userRole.getRoleName());
        preparedStatement.setInt(2, userRole.getUserRoleId());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

    }

    @Override
    public void deleteUserRole(UserRole userRole) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.DELETE(table, columns[0]));

        preparedStatement.setInt(1, userRole.getUserRoleId());
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

    }
}
