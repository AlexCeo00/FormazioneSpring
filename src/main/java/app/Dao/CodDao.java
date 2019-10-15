package app.Dao;

import app.model.Codicesc;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CodDao")
public class CodDao {
    @Autowired
    private SessionFactory sessionFactory;

    public CodDao(){}
    /**
     * Save Codicesc
     * @param cod
     */
    public void saveCod(Codicesc cod) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            // save the student object
            session.save(cod);
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
     * Update Codicesc
     * @param cod
     */
    public void updateCod(Codicesc cod) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(cod);
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
    public void deleteCod(int id) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // Delete a user object
            Codicesc cod = session.get(Codicesc.class, id);
            if (cod != null) {
                session.delete(cod);
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
    public Codicesc getCod(int id) {

        Transaction transaction = null;
        Codicesc cod = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an auto object
            cod = session.get(Codicesc.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return cod;
    }

    /**
     * Get all Codes
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Codicesc> getAllCod() {
        Transaction transaction = null;
        List <Codicesc> listOfCod = null;
        try (Session session =sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            listOfCod = session.createQuery("from Codicesc").getResultList();

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
