package com.maksim.model.hibernateImpl;

import com.maksim.controller.manager.Logs;
import com.maksim.model.dao.UserDao;
import com.maksim.model.domain.User;
import com.maksim.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Максим on 03/May/18.
 */
@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

//    private final static UserDaoImpl userDaoImpl = new UserDaoImpl();
//
//    private UserDaoImpl() {
//    }
//
//    static UserDaoImpl getInstance() {
//        return userDaoImpl;
//    }
    private SessionFactory sessionFactory ;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean addUser(User user) {

        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        logger.info(Logs.USER_LOADER);
        return true;

//
//        try {
//            session.beginTransaction();
//            session.save(user);
//            session.getTransaction().commit();
//            return true;
//
//        } catch (HibernateException he) {
//            if (session.getTransaction() != null) {
//                session.getTransaction().rollback();
//                logger.error(he.getMessage());
//            }
//            return false;
//        }
//        finally {
//            session.close();
//        }
    }

    @Override
    public void removeUser(int id) {

    }

    @Override
    public User findUserByLogin(String login) {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = DBConnection.getConnection();
//            statement = connection.prepareStatement(
//                    "SELECT * FROM USERS WHERE LOGIN = ?");
//            statement.setString(1, login);
//            resultSet = statement.executeQuery();
//            User user = createUserFromResult(resultSet);
//            return user;
//        } catch (SQLException e) {
//            logger.error(e.getMessage());
//        } finally {
//            DBConnection.close(connection, statement, resultSet);
//        }
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Query query = session.createQuery(" FROM User  WHERE login = :login");
        query.setString("login", login);
        User user = (User) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return user;

//
//        try {
//            session.beginTransaction();
//            Query query= session.createQuery("SELECT * FROM USERS WHERE LOGIN = ?");
//            query.setString(1,login);
//            User user = (User) query.uniqueResult();
//            session.getTransaction().commit();
//            return user;
//
//        } catch (HibernateException he) {
//            if (session.getTransaction() != null) {
//                session.getTransaction().rollback();
//                logger.error(he.getMessage());
//            }
//            return null;
//        } finally {
//            session.close();
//        }
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public User findUserById(int userId) {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = DBConnection.getConnection();
//            statement = connection.prepareStatement(
//                    "SELECT * FROM USERS WHERE USERID = ?");
//            statement.setInt(1, userId);
//            resultSet = statement.executeQuery();
////             if (resultSet!=null)
//            return createUserFromResult(resultSet);
//        } catch (SQLException e) {
//            logger.error(e.getMessage());
//        } finally {
//            DBConnection.close(connection, statement, resultSet);
//        }
//        return null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(" FROM User  WHERE userId= :userId");
        query.setParameter("userId", userId);
        User user = (User) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public User checkLoginAndPassword(String login, String password) {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = DBConnection.getConnection();
//            preparedStatement = connection.prepareStatement(
//                    "SELECT * FROM USERS WHERE LOGIN = ? AND PASSWORD = ?");
//            preparedStatement.setString(1, login);
//            preparedStatement.setString(2, password);
//            resultSet = preparedStatement.executeQuery();
//            User user = createUserFromResult(resultSet);
//            return user;
//        } catch (SQLException e) {
//            logger.error(e.getMessage());
//        } finally {
//            DBConnection.close(connection, preparedStatement, resultSet);
//        }
//        return null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(" FROM User  WHERE login= :login and password= :pass");
        query.setParameter("login", login);
        query.setParameter("pass", password);
        User user = (User) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return user;

    }

//    private User createUserFromResult(ResultSet resultSet) throws SQLException {
//        if (resultSet.isBeforeFirst()) resultSet.next();
//
//        int userId = resultSet.getInt(1);
//        String login = resultSet.getString(2);
//        String password = resultSet.getString(3);
//        String fullName = resultSet.getString(4);
//        String email = resultSet.getString(5);
//        BigDecimal account = resultSet.getBigDecimal(6);
//        return new User(userId, login, password, fullName, email, account);
//
//    }

    public boolean updateUser(User user) {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = DBConnection.getConnection();
//            preparedStatement = connection.prepareStatement(
//                    "UPDATE USERS  SET LOGIN = ?, PASSWORD = ?, FULLNAME = ?, EMAIL = ?, ACCOUNT= ? WHERE USERID = ? ");
//            preparedStatement.setString(1, user.getLogin());
//            preparedStatement.setString(2, user.getPassword());
//            preparedStatement.setString(3, user.getFullName());
//            preparedStatement.setString(4, user.getEmail());
//            preparedStatement.setBigDecimal(5, user.getAccount());
//            preparedStatement.setInt(6, user.getUserId());
//            preparedStatement.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            logger.error(e.getMessage());
//        } finally {
//            DBConnection.close(connection, preparedStatement, resultSet);
//        }
//        return false;


        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(" update User  SET LOGIN = :login, PASSWORD = :pass, FULLNAME = :fullName," +
                " EMAIL = :email, ACCOUNT= :account WHERE USERID = :userId ");
        query.setParameter("login", user.getLogin());
        query.setParameter("pass", user.getPassword());
        query.setParameter("fullName", user.getFullName());
        query.setParameter("email", user.getEmail());
        query.setParameter("account", user.getAccount());
        query.setParameter("userId", user.getUserId());

        int status=query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        if (status>0) {
            return true;
        }else return false;
    }
}
