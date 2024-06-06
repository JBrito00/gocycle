package isel.sisinf.model;

import isel.sisinf.model.interfaces.IReservation;
import jakarta.persistence.*;
import java.sql.Date;

@Entity
@NamedQuery(name = "Reservation.findByKey",
            query = "SELECT r FROM Reservation r WHERE r.reservationNumber =:key")
public class Reservation implements IReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startDate")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "endDate")
    private Date endDate;

    @MapsId("bicycle_id")
    @JoinColumn(name = "bicycle_id", nullable = false)
    private int bicycle;

    @MapsId("customer_id")
    @JoinColumn(name = "customer_id", nullable = false)
    private int customer;

    @Column(name = "price")
    private double price;

    public Reservation(int customerId, int bikeId, String startDateTime, String endDateTime, double price) {
        this.customer = customerId;
        this.bicycle = bikeId;
        this.startDate = new Date(Long.parseLong(startDateTime));
        this.endDate = new Date(Long.parseLong(endDateTime));
        this.price = price;
    }

    public Reservation() {}

    public int getNumber() {return reservationNumber;}
    public void setNumber(int number) {this.reservationNumber = number;}

    public int getBicycle() {return bicycle;}
    public void setBicycle(int bicycle) {this.bicycle = bicycle;}

    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}

    public Date getBeginingDate(){return startDate;}
    public void setBeginingDate(Date beginingDate) {this.startDate = beginingDate;}

    public Date getEndingDate(){return endDate;}
    public void setEndingDate(Date endingDate) {this.endDate = endingDate;}
}
