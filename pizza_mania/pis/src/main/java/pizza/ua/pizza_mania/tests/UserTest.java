package pizza.ua.pizza_mania.tests;

import pizza.ua.pizza_mania.dao.UserDAO;
import pizza.ua.pizza_mania.dao.impl.POSQLUserDAO;
import pizza.ua.pizza_mania.entities.User;

import java.sql.SQLException;

public class UserTest {
    public void UserOutput() throws SQLException {
        UserDAO userDAO = new POSQLUserDAO();
        System.out.println("\n\n___________________FOR_USER_____________________");
        System.out.println(userDAO.findAll());
        System.out.println("\n________ADDING_USERS_AND_THEIR_DISPLAY_________");
        User user1 = new User("Ihor", "But", "igorek@gmail.com", "1234", 1);
        System.out.println(userDAO.addUser(user1));
        User user2 = new User("Vlad", "Petrov", "vlados@gmail.com", "5678", 2);
        System.out.println(userDAO.addUser(user2));
        System.out.println(userDAO.findAll());
        System.out.println("\n______FIND_USER_BY_ID_AND_CHANGE_HIS_DATA______");
        user2 = userDAO.findById(2);
        user2.setUserPassword("4321");
        userDAO.updateUser(user2);
        System.out.println(userDAO.findAll());
        System.out.println("\n________________DELETING_USER_________________");
        userDAO.deleteUser(user1);
        System.out.println(userDAO.findAll());
    }
}
