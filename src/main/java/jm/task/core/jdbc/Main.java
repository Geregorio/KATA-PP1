package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserDao userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        //userDaoJDBC.dropUsersTable();
        //userDaoJDBC.saveUser("A", "AA", (byte) 11);
        userDaoJDBC.saveUser("B", "BB", (byte) 20);
        userDaoJDBC.saveUser("C", "CC", (byte) 30);
        userDaoJDBC.saveUser("D", "DD", (byte) 40);
        //userDaoJDBC.removeUserById(3);
        //System.out.println("ListOfUsers: " + userDaoJDBC.getAllUsers());
        //userDaoJDBC.cleanUsersTable();
    }
}
