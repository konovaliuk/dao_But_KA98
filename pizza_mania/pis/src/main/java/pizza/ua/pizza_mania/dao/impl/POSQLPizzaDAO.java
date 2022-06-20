package pizza.ua.pizza_mania.dao.impl;

import pizza.ua.pizza_mania.connection.PSQLConnector;
import pizza.ua.pizza_mania.dao.PizzaDAO;
import pizza.ua.pizza_mania.entities.Pizza;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class POSQLPizzaDAO implements PizzaDAO {

    Operations operations = new Operations();
    private static final String table = "pizza";
    private static final String[] columns = {"pizza_id","pizza_name","pizza_ingridients","pizza_size","pizza_price"};

    @Override
    public Pizza findById(int id) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.SELECT(table) + " WHERE "+columns[0] + "="+id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Pizza pizza = null;
        if(resultSet.next()){
            pizza = new Pizza(resultSet.getInt(columns[0]),resultSet.getString(columns[1]), resultSet.getString(columns[2])
                    ,resultSet.getInt(columns[3]), resultSet.getInt(columns[4]));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return pizza;
    }

    @Override
    public List<Pizza> findAll() throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.SELECT(table));
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Pizza> pizza = new ArrayList<>();
        while (resultSet.next()){
            pizza.add(new Pizza(resultSet.getInt(columns[0]),resultSet.getString(columns[1]), resultSet.getString(columns[2])
                    ,resultSet.getInt(columns[3]), resultSet.getInt(columns[4])));
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return pizza;
    }

    @Override
    public boolean addPizza(Pizza pizza) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.INSERT(table, columns), Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, pizza.getPizzaName());
        preparedStatement.setString(2, pizza.getPizzaIngridients());
        preparedStatement.setInt(3, pizza.getSize());
        preparedStatement.setInt(4, pizza.getPrice());

        if(preparedStatement.executeUpdate() == 1){
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                pizza.setPizzaId(resultSet.getInt(columns[0]));
            }
            resultSet.close();
        }
        preparedStatement.close();
        connection.close();
        return pizza.getPizzaId()!=null;
    }

    @Override
    public void updatePizza(Pizza pizza) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.UPDATE(table, columns));

        preparedStatement.setString(1, pizza.getPizzaName());
        preparedStatement.setString(2, pizza.getPizzaIngridients());
        preparedStatement.setInt(3, pizza.getSize());
        preparedStatement.setInt(4, pizza.getPrice());
        preparedStatement.setInt(5, pizza.getPizzaId());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

    }

    @Override
    public void deletePizza(Pizza pizza) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(operations.DELETE(table, columns[0]));

        preparedStatement.setInt(1, pizza.getPizzaId());
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

    }
}
