package isel.sisinf.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Customer")
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
public class Customer implements ICostumer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "emaill")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "cc")
    private String cc;

    @Column(name = "nationality")
    private String nationality;

    @OneToMany(mappedBy = "costumer")
    private List<Reservation> reservations;

    public int getId() {return customerId;}
    public void setId(int id) {this.customerId = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}

    public String getCC() {return cc;}
    public void setCC(String cc) {this.cc = cc;}

    public String getNationality() {return nationality;}
    public void setNationality(String nationality) {this.nationality = nationality;}

}
