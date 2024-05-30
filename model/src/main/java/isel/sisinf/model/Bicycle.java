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

    @Override
    public int getId() {
        return bicycleId;
    }

    @Override
    public void setId(int id) {
        this.bicycleId = id;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String name) {
        this.model = name;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public GearSystem getGearSystem() {
        return gearSystem;
    }

    @Override
    public void setGearSystem(GearSystem gearSystem) {
        this.gearSystem = gearSystem;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }
}



