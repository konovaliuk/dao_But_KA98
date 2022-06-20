package pizza.ua.pizza_mania.dao;

import pizza.ua.pizza_mania.entities.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {
    public Order findById(int id) throws SQLException;
    public List<Order> findAll() throws SQLException;
    public boolean addOrder(Order order) throws SQLException;
    public void updateOrder(Order order) throws SQLException;
    public void deleteOrder(Order order) throws SQLException;
}
