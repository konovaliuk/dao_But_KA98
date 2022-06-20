package pizza.ua.pizza_mania.tests;


import pizza.ua.pizza_mania.dao.OrderStatusDAO;
import pizza.ua.pizza_mania.dao.impl.POSQLOrderStatusDAO;
import pizza.ua.pizza_mania.entities.OrderStatus;

import java.sql.SQLException;

public class OrderStatusTest {
    public void OrderStatusOutput() throws SQLException {
        OrderStatusDAO ost = new POSQLOrderStatusDAO();
        System.out.println("\n\n_________________FOR_ORDER_STATUS___________________");
        System.out.println(ost.findAll());
        System.out.println("\n_______ADDING_ORDER_STATUS_AND_THEIR_DISPLAY________");
        OrderStatus order1 = new OrderStatus("accepted");
        System.out.println(ost.addOrderStatus(order1));
        OrderStatus order2 = new OrderStatus("confirmed");
        System.out.println(ost.addOrderStatus(order2));
        System.out.println(ost.findAll());
        System.out.println("\n______FIND_ORDER_STATUS_BY_ID_AND_CHANGE_HIS_DATA______");
        order2 = ost.findById(2);
        order2.setStatusDescription("jecected");
        ost.updateOrderStatus(order2);
        System.out.println(ost.findAll());
        System.out.println("\n_____________DELETING_ORDER_STATUS______________");
        ost.deleteOrderStatus(order2);
        System.out.println(ost.findAll());
    }
}
