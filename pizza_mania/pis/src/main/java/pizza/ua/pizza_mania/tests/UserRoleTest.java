package pizza.ua.pizza_mania.tests;

import pizza.ua.pizza_mania.dao.UserDAO;
import pizza.ua.pizza_mania.dao.UserRoleDAO;
import pizza.ua.pizza_mania.dao.impl.POSQLRoleDAO;
import pizza.ua.pizza_mania.dao.impl.POSQLUserDAO;
import pizza.ua.pizza_mania.entities.User;
import pizza.ua.pizza_mania.entities.UserRole;

import java.sql.SQLException;

public class UserRoleTest {
    public void UserRoleOutput() throws SQLException {
        UserRoleDAO urd = new POSQLRoleDAO();
        System.out.println("\n\n_________________FOR_USERS_ROLES_________________");
        System.out.println(urd.findAll());
        System.out.println("\n________ADDING_USERS_ROLES___________");
        UserRole role1 = new UserRole("client");
        System.out.println(urd.addUserRole(role1));
        UserRole role2 = new UserRole("admin");
        System.out.println(urd.addUserRole(role2));
        System.out.println(urd.findAll());
        System.out.println("\n______FIND_ROLE_BY_ID_AND_CHANGE_IT______");
        role2 = urd.findById(2);
        role2.setRoleName("MEGA admin");
        urd.updateUserRole(role2);
        System.out.println(urd.findAll());
        System.out.println("\n________________DELETING_USER_________________");
        urd.deleteUserRole(role2);
        System.out.println(urd.findAll());
    }
}
