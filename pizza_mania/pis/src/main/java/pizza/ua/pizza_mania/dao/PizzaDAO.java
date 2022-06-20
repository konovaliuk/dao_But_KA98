package pizza.ua.pizza_mania.dao;

import pizza.ua.pizza_mania.entities.Pizza;

import java.sql.SQLException;
import java.util.List;

public interface PizzaDAO {
    public Pizza findById(int id) throws SQLException;
    public List<Pizza> findAll() throws SQLException;
    public boolean addPizza(Pizza pizza) throws SQLException;
    public void updatePizza(Pizza pizza) throws SQLException;
    public void deletePizza(Pizza pizza) throws SQLException;
}
