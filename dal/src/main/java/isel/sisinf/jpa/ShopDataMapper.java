package isel.sisinf.jpa;

import isel.sisinf.jpa.genericInterfaces.IDataMapper;
import isel.sisinf.model.Shop;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class ShopDataMapper implements IDataMapper<Shop> {
    private EntityManager entityManager;

    public ShopDataMapper() {
        this.entityManager = Persistence.createEntityManagerFactory("Shop").createEntityManager();
    }

    public void create(Shop shop) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(shop);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void update(Shop shop) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(shop);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
    public void delete(Shop shop) {
        try {
            Shop s = entityManager.find(Shop.class, shop.getCode());
            if (s != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(s);
                entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}