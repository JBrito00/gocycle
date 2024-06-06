package isel.sisinf.model.mappers;

import isel.sisinf.model.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class ReservationDataMapper implements IDataMapper<Reservation> {
    private EntityManager entityManager;

    public ReservationDataMapper() {
        this.entityManager = Persistence.createEntityManagerFactory("Reservation").createEntityManager();
    }

    public void create(Reservation reservation) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(reservation);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void update(Reservation reservation) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(reservation);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
    public void delete(int reservationId) {
        try {
            Reservation r = entityManager.find(Reservation.class, reservationId);
            if (r != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(r);
                entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
