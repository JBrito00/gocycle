package isel.sisinf.model.procedures;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.sql.Connection;
import java.sql.SQLException;

//public class MakeBooking {
//    public void makeBooking(Integer bookingId, Integer customerId, Integer bikeId, String startDateTime, String endDateTime, Double price) throws SQLException {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gocycle-project");
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            Connection cn = em.unwrap(Connection.class);
//            cn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//            Query query = em.createNativeQuery("CALL makeBooking(?,?,?,?,?,?)");
//            query.setParameter(1, bookingId);
//            query.setParameter(2, customerId);
//            query.setParameter(3, bikeId);
//            query.setParameter(4, startDateTime);
//            query.setParameter(5, endDateTime);
//            query.setParameter(6, price);
//            query.executeUpdate();
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            if (em.getTransaction().isActive()) em.getTransaction().rollback();
//            throw e;
//        } finally {
//            em.close();
//            emf.close();
//        }
//    }
//}
