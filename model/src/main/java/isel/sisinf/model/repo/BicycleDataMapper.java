package isel.sisinf.model.repo;

import isel.sisinf.model.Bicycle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class BicycleDataMapper {
    private EntityManager entityManager;

    public BicycleDataMapper() {
        this.entityManager = Persistence.createEntityManagerFactory("Bicycle").createEntityManager();
    }

    public void insert(Bicycle bicycle) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(bicycle);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
            throw e;
        }
    }

    public Bicycle read(int bicycle){
        try{
            return entityManager.find(Bicycle.class, bicycle);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
