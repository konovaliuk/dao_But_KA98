package pizza.ua.pizza_mania.dao.impl;

import pizza.ua.pizza_mania.connection.PSQLConnector;
import pizza.ua.pizza_mania.dao.UserDAO;
import pizza.ua.pizza_mania.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class POSQLUserDAO implements UserDAO {

    Operations operations = new Operations();
    private static final String table = "users";
    private static final String[] columns = {"user_id","user_name","user_surname","user_email","user_password", "role_id"};

    @Override
    public User findById(int id) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.SELECT(table) + " WHERE "+columns[0] + "="+id);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        if(resultSet.next()){
            user = new User(resultSet.getInt(columns[0]),resultSet.getString(columns[1]), resultSet.getString(columns[2])
            ,resultSet.getString(columns[3]), resultSet.getString(columns[4]),resultSet.getInt(columns[5]));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return user;
    }

    @Override
    public List<User> findAll() throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.SELECT(table));
        ResultSet resultSet = preparedStatement.executeQuery();

        List<User> users = new ArrayList<>();
        while (resultSet.next()){
            users.add(new User(resultSet.getInt(columns[0]),resultSet.getString(columns[1]), resultSet.getString(columns[2])
                    ,resultSet.getString(columns[3]), resultSet.getString(columns[4]),resultSet.getInt(columns[5])));
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return users;
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.INSERT(table, columns), Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getUserSurname());
        preparedStatement.setString(3, user.getUserEmail());
        preparedStatement.setString(4, user.getUserPassword());
        preparedStatement.setInt(5, user.getRoleId());

        if(preparedStatement.executeUpdate() == 1){
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                user.setUserId(resultSet.getInt(columns[0]));
            }
            resultSet.close();
        }
        preparedStatement.close();
        connection.close();
        return user.getUserId()!=null;
    }

    @Override
    public void updateUser(User user) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.UPDATE(table, columns));

        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getUserSurname());
        preparedStatement.setString(3, user.getUserEmail());
        preparedStatement.setString(4, user.getUserPassword());
        preparedStatement.setInt(5, user.getRoleId());
        preparedStatement.setInt(6, user.getUserId());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.DELETE(table, columns[0]));

        preparedStatement.setInt(1, user.getUserId());
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

    }

}
