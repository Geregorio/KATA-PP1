package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users_table (Id INT AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(20), LastName VARCHAR(20), Age INT)");
            System.out.println("createUsersTable() OK");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("createUsersTable() ERROR");
        }
    }

    public void dropUsersTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS users_table");
            System.out.println("dropUsersTable() OK");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("dropUsersTable() ERROR");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("INSERT users_table (Name, LastName, Age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.execute();
            System.out.println(String.format("User с именем — %s добавлен в базу данных", name));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("saveUser() ERROR");
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("DELETE FROM users_table WHERE ID = ?")) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            preparedStatement.execute();
            System.out.println("removeUserById() OK");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("removeUserById() ERROR");
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users_table");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("Name"));
                user.setLastName(resultSet.getString("LastName"));
                user.setAge(resultSet.getByte("Age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("getAllUsers() ERROR");
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM users_table");
            System.out.println("cleanUsersTable() OK");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("cleanUsersTable() ERROR");
        }
    }
}