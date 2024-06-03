package isel.sisinf.model.repo;

import isel.sisinf.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomerRepository implements IRepository<Customer, List<Customer>, Integer> {

    private EntityManager entityManager;
    private final BicycleDataMapper mapper;

    public CustomerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.mapper = new BicycleDataMapper();
    }

    @Override
    public Customer findByKey(Integer key) {
        try{
            return entityManager.find(Customer.class, key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Customer> find(String jpql, Object... params) {
        TypedQuery<Customer> query = entityManager.createQuery(jpql, Customer.class);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.getResultList();
    }
}
