package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        //userDaoJDBC.createUsersTable();
        //userDaoJDBC.dropUsersTable();
        //userDaoJDBC.saveUser("A", "AA", (byte) 11);
        //userDaoJDBC.saveUser("B", "BB", (byte) 22);
        //userDaoJDBC.saveUser("C", "CC", (byte) 33);
        //userDaoJDBC.saveUser("D", "DD", (byte) 44);
        //userDaoJDBC.removeUserById(3);
        //System.out.println("ListOfUsers: " + userDaoJDBC.getAllUsers());
        //userDaoJDBC.cleanUsersTable();
    }
}
