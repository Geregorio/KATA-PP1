package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = String.format("CREATE TABLE %s " +
                "(Id INT AUTO_INCREMENT PRIMARY KEY, " +
                "Name VARCHAR(20), LastName VARCHAR(20), " +
                "Age INT)", Util.TABLENAME);
        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("createUsersTable() OK");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("createUsersTable() ERROR");
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE " + Util.TABLENAME;
        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("dropUsersTable() OK");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("dropUsersTable() ERROR");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = String.format("INSERT %s (Name, LastName, Age) " +
                "VALUES (%s, %s, %d)", Util.TABLENAME, "\"" + name + "\"", "\"" + lastName + "\"", age);
        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println(String.format("User с именем — %s добавлен в базу данных", name));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("saveUser() ERROR");
            System.out.println(sql);
        }
    }

    public void removeUserById(long id) {
        String sql = String.format("DELETE FROM %s WHERE ID = %d", Util.TABLENAME, id);
        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("removeUserById() OK");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("removeUserById() ERROR");
            System.out.println(sql);
        }
    }

    public List<User> getAllUsers() {
        String sql = String.format("SELECT * FROM %s", Util.TABLENAME);
        List<User> userList = new ArrayList<>();
        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
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
        String sql = String.format("DELETE FROM %s", Util.TABLENAME);
        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("cleanUsersTable() OK");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("cleanUsersTable() ERROR");
        }
    }
}
