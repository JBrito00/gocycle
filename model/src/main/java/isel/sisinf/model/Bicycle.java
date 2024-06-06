package isel.sisinf.model;

import isel.sisinf.model.interfaces.IBicycle;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "bicycle")
@NamedQuery(name = "Bicycle.findAll",
        query = "SELECT b FROM Bicycle b")
public class Bicycle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bicycleId")
    private int bicycleId;

//    @Column(name = "id", unique = true, nullable = false)
//    private String id;

    @Column(name = "weight")
    private double weight;

    @Column(name = "model")
    private String model;

    @Column(name = "brand")
    private String brand;

    @Enumerated(EnumType.STRING)
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

    public Integer getId() {return this.bicycleId;}
    public void setId(int id) {this.bicycleId = id;}

    public String getModel() {return this.model;}
    public void setModel(String name) {this.model = name;}

    public double getWeight() {return this.weight;}
    public void setWeight(double weight) {this.weight = weight;}

    public String getBrand() {return this.brand;}
    public void setBrand(String brand) {this.brand = brand;}

    public GearSystem getGearSystem() {return this.gearSystem;}
    public void setGearSystem(GearSystem gearSystem) {this.gearSystem = gearSystem;}

    public Status getStatus() {return this.status;}
    public void setStatus(Status status) {this.status = status;}
}



