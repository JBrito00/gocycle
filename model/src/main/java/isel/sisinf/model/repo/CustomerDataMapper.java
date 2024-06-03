package isel.sisinf.model.repo;

import isel.sisinf.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class CustomerDataMapper implements IDataMapper<Customer>{
    private EntityManager entityManager;

    public CustomerDataMapper() {
        this.entityManager = Persistence.createEntityManagerFactory("Customers").createEntityManager();
    }

    public void create(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void update(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(customer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
    public void delete(Customer customer) {
        try {
            Customer c = entityManager.find(Customer.class, customer.getId());
            if (c != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(c);
                entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
