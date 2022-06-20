package pizza.ua.pizza_mania.dao;

import pizza.ua.pizza_mania.entities.OrderCheck;

import java.sql.SQLException;
import java.util.List;

public interface OrderCheckDAO {
    public OrderCheck findById(int id) throws SQLException;
    public List<OrderCheck> findAll() throws SQLException;
    public boolean addOrderCheck(OrderCheck orderCheck) throws SQLException;
    public void updateOrderCheck(OrderCheck orderCheck) throws SQLException;
    public void deleteOrderCheck(OrderCheck orderCheck) throws SQLException;
}
