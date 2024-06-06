package isel.sisinf.model.procedures;

import isel.sisinf.model.Bicycle;
import isel.sisinf.model.Customer;
import isel.sisinf.model.Reservation;
import isel.sisinf.model.repo.BicycleRepository;
import isel.sisinf.model.repo.CustomerRepository;
import isel.sisinf.model.repo.ReservationRepository;
import jakarta.persistence.EntityManager;
import java.util.List;

public class Service {

    private final EntityManager _em;
    private CustomerRepository customerRepository;
    private BicycleRepository bicycleRepository;
    private ReservationRepository reservationRepository;

    public Service(EntityManager em) {
        this._em = em;
        this.customerRepository = new CustomerRepository(em);
        this.bicycleRepository = new BicycleRepository(em);
        this.reservationRepository = new ReservationRepository(em);
    }

    public void createCustomer(Customer customer){
        customerRepository.add(customer);
    }

    public List<Bicycle> listExistingBikes(){
        return bicycleRepository.find("select * from Bicycle", Bicycle.class);
    }

    public Bicycle checkBikeAvailability(int bicycleId){
        return bicycleRepository.findByKey(bicycleId);
    }

    public List<Reservation> obtainBookings(){
        return reservationRepository.find("select * from Reservation", Reservation.class);
    }

    public void makeBooking(Reservation reservation){
        reservationRepository.add(reservation);
    }

    public void cancelBooking(Integer reservationId){
        reservationRepository.delete(reservationId);
    }
}
