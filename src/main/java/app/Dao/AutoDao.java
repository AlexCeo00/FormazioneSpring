package app.Dao;

import app.model.Auto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("AutoDao")
public class AutoDao {
    @Autowired
    private SessionFactory sessionFactory;

    public AutoDao(){}
    /**
     * Save Auto
     * @param auto
     */
    public void saveAuto(Auto auto) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            // save the student object
            session.save(auto);
            // commit transaction
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    /**
     * Update Auto
     * @param auto
     */
    public void updateAuto(Auto auto) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(auto);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Delete Auto
     * @param id
     */
    public void deleteAuto(int id) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // Delete a user object
            Auto auto = session.get(Auto.class, id);
            if (auto != null) {
                session.delete(auto);
                System.out.println("auto is deleted");
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Get Auto By ID
     * @param id
     * @return
     */
    public Auto getAuto(int id) {

        Transaction transaction = null;
        Auto auto = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an auto object
            auto = session.get(Auto.class,id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return auto;
    }

    /**
     * Get all Autos
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Auto> getAllAuto() {
        Transaction transaction = null;
        List <Auto> listOfAuto = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            listOfAuto = session.createQuery("from Auto").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfAuto;
    }
}
