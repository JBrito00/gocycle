package isel.sisinf.model;

import jakarta.persistence.*;

@Entity
@NamedQuery(name = "GpsDevice.findByKey", query = "SELECT g FROM GpsDevice g")
public class GpsDevice implements IGPS{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "battery", nullable = false)
    private int battery;

    @OneToOne
    @JoinColumn(name = "bicycle_id", nullable = false)
    private Bicycle bicycle;

    public int getNumber() {return number;}
    public void setNumber(int number) {this.number = number;}

    public double getLatitude() {return latitude;}
    public void setLatitude(double latitude) {this.latitude = latitude;}

    public double getLongitude() {return longitude;}
    public void setLongitude(double longitude) {this.longitude = longitude;}

    public int getBatery() {return battery;}
    public void setBatery(int batery) {this.battery = batery;}
}
