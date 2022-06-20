package pizza.ua.pizza_mania;

import pizza.ua.pizza_mania.dao.UserDAO;
import pizza.ua.pizza_mania.dao.UserRoleDAO;
import pizza.ua.pizza_mania.dao.impl.POSQLUserDAO;
import pizza.ua.pizza_mania.entities.OrderStatus;
import pizza.ua.pizza_mania.entities.User;
import pizza.ua.pizza_mania.tests.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserTest ut = new UserTest();
        UserRoleTest urt = new UserRoleTest();
        PizzaTest pt = new PizzaTest();
        OrderTest ot = new OrderTest();
        OrderStatusTest ost = new OrderStatusTest();
        OrderCheckTest oct = new OrderCheckTest();

        urt.UserRoleOutput();
        ut.UserOutput();
        pt.PizzaOutput();
        ost.OrderStatusOutput();
        ot.OrderOutput();
        oct.OrderCheckOutput();
    }
}
