package app.Dao;

import app.model.Ruoli;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("RuoliDao")
public class RuoliDao {

    public RuoliDao(){}
    /**
     * Save Codicesc
     * @param ruo
     */
    @Autowired
    private SessionFactory sessionFactory;

    public void saveRuo(Ruoli ruo) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            // save the student object
            session.save(ruo);
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
     * Update ruo
     * @param ruo
     */
    public void updateRuo(Ruoli ruo) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(ruo);
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
     * Delete Codicesc
     * @param id
     */
    public void deleteRuo(int id) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // Delete a user object
            Ruoli ruo = session.get(Ruoli.class, id);
            if (ruo != null) {
                session.delete(ruo);
                System.out.println("cod is deleted");
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
     * Get Codicesc by ID
     * @param id
     * @return
     */
    public Ruoli getRuo(int id) {

        Transaction transaction = null;
        Ruoli ruo = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an auto object
            ruo = session.get(Ruoli.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return ruo;
    }

    /**
     * Get all Codes
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Ruoli> getAllRuo() {
        Transaction transaction = null;
        List <Ruoli> listOfCod = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            listOfCod = session.createQuery("from Ruoli").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfCod;
    }
}
