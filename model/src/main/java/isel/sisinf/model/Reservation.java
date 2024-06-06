package isel.sisinf.model;

import isel.sisinf.model.interfaces.IReservation;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "reservation")
@NamedQuery(name = "Reservation.findAll",
        query = "SELECT r FROM Bicycle r")
public class Reservation implements IReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "bicycle_id", nullable = false)
    private Bicycle bicycle;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "price")
    private double price;

    public Reservation(Customer customerId, Bicycle bikeId, String startDateTime, String endDateTime, double price) {
        this.customer = customerId;
        this.bicycle = bikeId;
        this.startDate = new Date(Long.parseLong(startDateTime));
        this.endDate = new Date(Long.parseLong(endDateTime));
        this.price = price;
    }

    public Reservation() {
    }

    public int getNumber() {
        return reservationNumber;
    }

    public void setNumber(int number) {
        this.reservationNumber = number;
    }

    public int getBicycle() {
        return bicycle.getId();
    }

    public void setBicycle(Bicycle bicycle) {
        this.bicycle = bicycle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getBeginingDate() {
        return startDate;
    }

    public void setBeginingDate(Date beginingDate) {
        this.startDate = beginingDate;
    }

    public Date getEndingDate() {
        return endDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endDate = endingDate;
    }

    public Customer getCustomer() {
        return customer;
    }
}
