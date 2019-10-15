package app.Dao;

import app.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository("LoginDao")
public class LoginDao{
    @Autowired
    private SessionFactory sessionFactory;

    public User isPresent(User user) throws ClassNotFoundException {
        try (Session session =sessionFactory.openSession()) {
            String hql = "from User where nome = ?1 and passut = ?2";
            User result = (User) session.createQuery(hql)
                    .setParameter(1, user.getNome())
                    .setParameter(2, user.getPassut())
                    .uniqueResult();
            if(result != null){
                return result;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
