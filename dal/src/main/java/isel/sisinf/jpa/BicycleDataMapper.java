package isel.sisinf.jpa;

import isel.sisinf.jpa.genericInterfaces.IDataMapper;
import isel.sisinf.model.Bicycle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class BicycleDataMapper implements IDataMapper<Bicycle> {
    private EntityManager entityManager;

    public BicycleDataMapper() {
        this.entityManager = Persistence.createEntityManagerFactory("Bicycle").createEntityManager();
    }

    public void create(Bicycle bicycle) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(bicycle);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void update(Bicycle bicycle) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(bicycle);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(Bicycle bicycle) {
        try {
            Bicycle b = entityManager.find(Bicycle.class, bicycle.getId());
            if (b != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(b);
                entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
