package pizza.ua.pizza_mania.tests;

import pizza.ua.pizza_mania.dao.PizzaDAO;
import pizza.ua.pizza_mania.dao.impl.POSQLPizzaDAO;
import pizza.ua.pizza_mania.entities.Pizza;

import java.sql.SQLException;

public class PizzaTest {
    public void PizzaOutput() throws SQLException {
        PizzaDAO pd = new POSQLPizzaDAO();
        System.out.println("\n\n_________________FOR_PIZZA__________________");
        System.out.println(pd.findAll());
        System.out.println("\n_______ADDING_PIZZAS_AND_THEIR_DISPLAY_______");
        Pizza pizza1 = new Pizza("Paperoni", "tomatos++", 30, 50);
        System.out.println(pd.addPizza(pizza1));
        Pizza pizza2 = new Pizza("Paperoni2", "meet++", 45, 100);
        System.out.println(pd.addPizza(pizza2));
        System.out.println(pd.findAll());

        System.out.println("\n______FIND_PIZZA_BY_ID_AND_CHANGE_HIS_DATA______");
        pizza2 = pd.findById(2);
        pizza2.setPrice(200);
        pd.updatePizza(pizza2);
        System.out.println(pd.findAll());
        System.out.println("\n________________DELETING_ORDER_CHECK_________________");
        pd.deletePizza(pizza2);
        System.out.println(pd.findAll());
    }
}
