package pizza.ua.pizza_mania.dao;

import pizza.ua.pizza_mania.entities.OrderStatus;

import java.sql.SQLException;
import java.util.List;

public interface OrderStatusDAO {
    public OrderStatus findById(int id) throws SQLException;
    public List<OrderStatus> findAll() throws SQLException;
    public boolean addOrderStatus(OrderStatus orderStatus) throws SQLException;
    public void updateOrderStatus(OrderStatus orderStatus) throws SQLException;
    public void deleteOrderStatus(OrderStatus orderStatus) throws SQLException;
}
