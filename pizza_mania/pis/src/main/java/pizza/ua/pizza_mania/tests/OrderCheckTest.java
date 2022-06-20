package pizza.ua.pizza_mania.tests;

import pizza.ua.pizza_mania.dao.OrderCheckDAO;
import pizza.ua.pizza_mania.dao.impl.POSQLOrderCheckDAO;
import pizza.ua.pizza_mania.entities.OrderCheck;
import java.sql.Date;
import java.sql.Time;

import java.sql.SQLException;

public class OrderCheckTest {
    public void OrderCheckOutput() throws SQLException {
        OrderCheckDAO ocd = new POSQLOrderCheckDAO();
        System.out.println("\n\n_________________FOR_ORDER_CHECKS__________________");
        System.out.println(ocd.findAll());
        System.out.println("\n_______ADDING_ORDER_CHECKS_AND_THEIR_DISPLAY_______");
        OrderCheck order1 = new OrderCheck(2,23,Date.valueOf("2022-05-05"),Time.valueOf("13:12:11"),200);
        System.out.println(ocd.addOrderCheck(order1));
        OrderCheck order2 = new OrderCheck(1,24,Date.valueOf("2022-04-05"),Time.valueOf("16:11:10"),100);
        System.out.println(ocd.addOrderCheck(order2));

        System.out.println(ocd.findAll());
        System.out.println("\n______FIND_ORDER_CHECK_BY_ID_AND_CHANGE_HIS_DATA______");
        order2 = ocd.findById(2);
        order2.setTotalUserCheck(150);
        ocd.updateOrderCheck(order2);
        System.out.println(ocd.findAll());
        System.out.println("\n________________DELETING_ORDER_CHECK_________________");
        ocd.deleteOrderCheck(order2);
        System.out.println(ocd.findAll());
    }
}
