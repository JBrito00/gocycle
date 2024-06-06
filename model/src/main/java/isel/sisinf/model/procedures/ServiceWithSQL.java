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

    public void createCustomer(String name, String address, String email, String phone, String CC, String nationality) throws SQLException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gocycle-project");
        EntityManager em = emf.createEntityManager();
        try {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    public void listExistingBikes() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gocycle-project");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            String sql = "SELECT b FROM Bicycle b";
            Query query1 = em.createQuery(sql);
            List<Bicycle> lr = query1.getResultList();
            System.out.println("Bicycles:");
            for (Bicycle b : lr) {
                System.out.printf("gid = %s ,", b.getId());
                System.out.printf("weight = %s ,", b.getWeight());
                System.out.printf("model = %s ,", b.getModel());
                System.out.printf("brand = %s ,\n", b.getBrand());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    public void checkBikeAvailability(int bicycleId, String date) throws SQLException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gocycle-project");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery(" SELECT checkBikeAvailability(?1, ?2)");
            query.setParameter(1, bicycleId);
            query.setParameter(2, date);
            List<Boolean> result = query.getResultList();
            if (result.get(0)) {
                System.out.println("Bike is available");
            } else {
                System.out.println("Bike is not available");
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    public void obtainBookings() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gocycle-project");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            String sql = "SELECT r FROM Reservation r";
            Query query1 = em.createQuery(sql);
            List<Reservation> lr = query1.getResultList();
            System.out.println("Bookings:");
            for (Reservation r : lr) {
                System.out.printf("gid = %s ,", r.getNumber());
                System.out.printf("starting date = %s ,", r.getBeginingDate());
                System.out.printf("end date = %s ,", r.getEndingDate());
                System.out.printf("bike = %s ,", r.getBicycle());
                System.out.printf("customer = %s, ", r.getCustomer());
                System.out.printf("price = %s ,\n", r.getPrice());
            }
            em.getTransaction().commit();
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

    public void cancelBooking(int reservationId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gocycle-project");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            String sql = "DELETE FROM Reservation r WHERE r.reservationNumber = ?1";
            Query query = em.createQuery(sql);
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

