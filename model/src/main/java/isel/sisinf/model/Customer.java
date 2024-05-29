package isel.sisinf.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Customer")
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    public Customer() { }

    public Long getId() {return this.id;}
    public void setId(Integer id) {this.id = id;}

    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    public String getAddress() {return this.address;}
    public void setAddress(String address) {this.address = address;}

    public String getEmail() {return this.email;}
    public void setEmail(String email) {this.email = email;}

    public String getPhone() {return this.phone;}
    public void setPhone(String phone) {this.phone = phone;}

    public String getCc() {return this.cc;}
    public void setCc(String cc) {this.cc = cc;}

    public String getNationality() {return this.nationality;}
    public void setNationality(String nationality) {this.nationality = nationality;}

}
