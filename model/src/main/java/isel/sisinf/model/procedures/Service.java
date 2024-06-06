package isel.sisinf.model.procedures;

import isel.sisinf.model.Bicycle;
import isel.sisinf.model.Customer;
import isel.sisinf.model.Reservation;
import isel.sisinf.model.repo.BicycleRepository;
import isel.sisinf.model.repo.CustomerRepository;
import isel.sisinf.model.repo.ReservationRepository;
import jakarta.persistence.EntityManager;

import java.sql.SQLException;
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

    public void createCustomer(Customer customer) throws SQLException{
        customerRepository.add(customer);
    }

    public List<Bicycle> listExistingBikes() throws SQLException {
        return bicycleRepository.find("select * from Bicycle", Bicycle.class);
    }

    public Bicycle checkBikeAvailability(int bicycleId) throws SQLException {
        return bicycleRepository.findByKey(bicycleId);
    }

    public List<Reservation> obtainBookings() throws SQLException {
        return reservationRepository.find("select * from Reservation", Reservation.class);
    }

    public void makeBooking(Reservation reservation) throws SQLException {
        reservationRepository.add(reservation);
    }

    public void cancelBooking(Integer reservationId) throws SQLException {
        reservationRepository.delete(reservationId);
    }
}
