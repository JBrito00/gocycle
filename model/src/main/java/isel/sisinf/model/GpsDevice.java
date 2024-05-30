package isel.sisinf.model;

import jakarta.persistence.*;

@Entity
@NamedQuery(name = "GpsDevice.findByKey", query = "SELECT g FROM GpsDevice g")
public class GpsDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "serialNumber", unique = true, nullable = false)
    private String serialNumber;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "batteryPercentagel", nullable = false)
    private int batteryPercentage;

    @OneToOne
    @JoinColumn(name = "bicycle_id", nullable = false)
    private Bicycle bicycle;
}
