package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        byte eleven = 11;
        byte twenty = 20;
        byte thirty = 30;
        byte forty = 40;
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        //userDaoJDBC.createUsersTable();
        //userDaoJDBC.dropUsersTable();
        //userDaoJDBC.saveUser("A", "AA", eleven);
        //userDaoJDBC.saveUser("B", "BB", twenty);
        //userDaoJDBC.saveUser("C", "CC", thirty);
        //userDaoJDBC.saveUser("D", "DD", forty);
        userDaoJDBC.removeUserById(3);
        //System.out.println("ListOfUsers: " + userDaoJDBC.getAllUsers());
        //userDaoJDBC.cleanUsersTable();
    }
}
