package pizza.ua.pizza_mania.tests;

import pizza.ua.pizza_mania.dao.OrderDAO;
import pizza.ua.pizza_mania.dao.impl.POSQLOrderDAO;
import pizza.ua.pizza_mania.entities.Order;

import java.sql.SQLException;

public class OrderTest {
    public void OrderOutput() throws SQLException {
        OrderDAO od = new POSQLOrderDAO();
        System.out.println("\n\n___________________FOR_ORDER_____________________");
        System.out.println(od.findAll());
        System.out.println("\n________ADDING_ORDERS_AND_THEIR_DISPLAY_________");
        Order order1 = new Order(23,2,2,200,1);
        System.out.println(od.addOrder(order1));
        Order order2 = new Order(25,1,1,50,1);
        System.out.println(od.addOrder(order2));
        System.out.println(od.findAll());
        System.out.println("\n______FIND_ORDER_BY_ID_AND_CHANGE_HIS_DATA______");
        order2 = od.findById(2);
        order2.setOrderStatusId(2);
        od.updateOrder(order2);
        System.out.println(od.findAll());
        System.out.println("\n________________DELETING_ORDER_________________");
        od.deleteOrder(order2);
        System.out.println(od.findAll());
    }
}
