package isel.sisinf.model.repo;

import isel.sisinf.model.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ReservationRepository implements IRepository<Reservation, List<Reservation>, Integer> {

    private EntityManager entityManager;
    private final ReservationDataMapper mapper;

    public ReservationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.mapper = new ReservationDataMapper();
    }

    @Override
    public Reservation findByKey(Integer key) {
        try{
            return entityManager.find(Reservation.class, key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Reservation> find(String jpql, Object... params) {
        TypedQuery<Reservation> query = entityManager.createQuery(jpql, Reservation.class);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.getResultList();
    }
}
