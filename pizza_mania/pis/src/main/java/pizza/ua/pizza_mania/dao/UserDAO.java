package pizza.ua.pizza_mania.dao;

import pizza.ua.pizza_mania.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    public User findById(int id) throws SQLException;
    public List<User> findAll() throws SQLException;
    public boolean addUser(User user) throws SQLException;
    public void updateUser(User user) throws SQLException;
    public void deleteUser(User user) throws SQLException;
}
