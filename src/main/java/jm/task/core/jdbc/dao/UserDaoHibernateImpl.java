package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import javax.transaction.SystemException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl extends HibernateUtil implements UserDao {
    private static final SessionFactory sessionFactory = getSessionFactory();
    String sql;
    Transaction transaction = null;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        sql = "CREATE TABLE IF NOT EXISTS " +
                "users_table (Id INT AUTO_INCREMENT PRIMARY KEY, " +
                "Name VARCHAR(20), LastName VARCHAR(20), Age INT)";
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        sql = "DROP TABLE IF EXISTS users_table";
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        User user = sessionFactory.openSession().get(User.class, id);
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        sql = "From User";
        List<User> listOfUsers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            listOfUsers = session.createQuery(sql).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfUsers;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DELETE FROM users_table");
            query.executeUpdate();
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
