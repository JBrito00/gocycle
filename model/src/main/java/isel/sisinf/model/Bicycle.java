package isel.sisinf.model;

import jakarta.persistence.*;

import java.util.List;

public class Bicycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id", unique = true, nullable = false)
    private String identifier;

    @Column(name = "weight")
    private int weight;

    @Column(name = "model")
    private String model;

    @Column(name = "brand")
    private String brand;

    @Column(nullable = false)
    private GearSystem gearSystem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @OneToOne(mappedBy = "bicycle", cascade = CascadeType.ALL)
    private GpsDevice gpsDevice;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @OneToMany(mappedBy = "bicycle")
    private List<Reservation> reservations;

    @Column
    private Integer range;

    @Column
    private Integer maxSpeed;



    private enum Status {
        Free,
        OCCUPY,
        INT_RESERVE,
        UNDER_MAINTENANCE
    }
}
