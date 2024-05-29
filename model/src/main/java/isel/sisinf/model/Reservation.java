package isel.sisinf.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id", unique = true, nullable = false)
    private String reservationNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startDate")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "endDate")
    private Date endDate;

    @MapsId("bicycle_id")
    @ManyToOne
    @JoinColumn(name = "bicycle_id", nullable = false)
    private Bicycle bicycle;

    @MapsId("customer_id")
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "price")
    private double price;

}
