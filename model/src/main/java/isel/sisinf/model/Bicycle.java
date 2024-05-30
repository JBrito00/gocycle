package isel.sisinf.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQuery(name = "Bicycle.findByKey",
        query = "SELECT b FROM Bicycle b WHERE b.bicycleId =:key")
public class Bicycle implements IBicycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bicycleId;

    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "weight")
    private double weight;

    @Column(name = "model")
    private String model;

    @Column(name = "brand")
    private String brand;

    @Column(nullable = false)
    private GearSystem gearSystem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    /*
        @OneToOne(mappedBy = "bicycle", cascade = CascadeType.ALL)
        private GpsDevice gpsDevice;

        @ManyToOne
        @JoinColumn(name = "shopCode", nullable = false)
        private Shop shop;
     */
    @OneToMany(mappedBy = "bicycle")
    private List<Reservation> reservations;

    @Column
    private Integer range;

    @Column
    private Integer maxSpeed;

    public int getId() {return bicycleId;}
    public void setId(int id) {this.bicycleId = id;}

    public String getModel() {return model;}
    public void setModel(String name) {this.model = name;}

    public double getWeight() {return weight;}
    public void setWeight(double weight) {this.weight = weight;}

    public String getBrand() {return brand;}
    public void setBrand(String brand) {this.brand = brand;}

    public GearSystem getGearSystem() {return gearSystem;}
    public void setGearSystem(GearSystem gearSystem) {this.gearSystem = gearSystem;}

    public Status getStatus() {return status;}
    public void setStatus(Status status) {this.status = status;}
}



