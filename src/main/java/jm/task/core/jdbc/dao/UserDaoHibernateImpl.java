package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.management.Query;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class UserDaoHibernateImpl extends HibernateUtil implements UserDao {
    private static final SessionFactory sessionFactory = getSessionFactory();
    
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable(){
        try (Session session = sessionFactory.openSession()) {
            Query query = (Query) session.createSQLQuery("CREATE TABLE IF NOT EXISTS " +
                    "users_table (Id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "Name VARCHAR(20), LastName VARCHAR(20), Age INT)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Query query = (Query) session.createSQLQuery("DROP TABLE IF EXISTS users_table");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (SystemException ex) {
                    throw new RuntimeException(ex);
                }
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        User user = sessionFactory.openSession().get(User.class, id);
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (SystemException ex) {
                    throw new RuntimeException(ex);
                }
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listOfUsers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            listOfUsers = session.createQuery("From User").list();
            return listOfUsers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfUsers;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.clear();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
