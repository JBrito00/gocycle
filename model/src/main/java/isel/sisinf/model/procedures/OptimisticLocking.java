package isel.sisinf.model.procedures;

import isel.sisinf.model.Customer;
import isel.sisinf.model.repo.BicycleRepository;
import isel.sisinf.model.repo.CustomerRepository;
import isel.sisinf.model.repo.ReservationRepository;
import jakarta.persistence.EntityManager;

import java.sql.SQLException;

public class OptimisticLocking {

    private final EntityManager _em;
    private CustomerRepository customerRepository;

    public OptimisticLocking(EntityManager em) {
        this._em = em;
        this.customerRepository = new CustomerRepository(em);
    }

    public void createCustomer(Customer customer) throws Exception {
        try {
            customerRepository.add(customer);
        } catch (Exception e) {
            System.out.println("Failed to create customer due to optimistic lock exception: " + e.getMessage());
            throw e;
        }
    }

}
