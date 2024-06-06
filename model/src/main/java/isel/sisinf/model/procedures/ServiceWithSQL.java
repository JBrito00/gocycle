package isel.sisinf.model.procedures;

import isel.sisinf.model.Bicycle;
import isel.sisinf.model.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ServiceWithSQL {

    public void createCustomer(String name, String address, String email, String phone, String CC, String nationality) throws SQLException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gocycle-project");
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Query query = em.createNativeQuery("CALL createCustomer(?1,?2,?3,?4,?5,?6)");
            query.setParameter(1, name);
            query.setParameter(2, address);
            query.setParameter(3, email);
            query.setParameter(4, phone);
            query.setParameter(5, CC);
            query.setParameter(6, nationality);
            query.executeUpdate();
            em.getTransaction().commit();
        } catch(Exception e){
            System.out.println(e.getMessage());
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    public List<Bicycle> listExistingBikes() throws SQLException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gocycle-project");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery("select * from Bicycle");
            List<Bicycle> result = query.getResultList();
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    public List<Bicycle> checkBikeAvailability(int bicycleId) throws SQLException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gocycle-project");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery("select * from Bicycle where bicycleId = ?1");
            query.setParameter(1, bicycleId);
            List<Bicycle> result = query.getResultList();
            return result;
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    public List<Reservation> obtainBookings() throws SQLException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gocycle-project");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery("select * from Reservation");
            List<Reservation> result = query.getResultList();
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    public void makeBooking(int customerId, int bikeId, String startDateTime, String endDateTime, double price) throws SQLException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gocycle-project");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Connection cn = em.unwrap(Connection.class);
            cn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            Query query = em.createNativeQuery("CALL makeBooking(?1,?2,?3,?4,?5)");
            query.setParameter(1, customerId);
            query.setParameter(2, bikeId);
            query.setParameter(3, startDateTime);
            query.setParameter(4, endDateTime);
            query.setParameter(5, price);
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    public void cancelBooking(int reservationId) throws SQLException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gocycle-project");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery("CALL cancelBooking(?1)");
            query.setParameter(1, reservationId);
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }
}

