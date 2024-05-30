package isel.sisinf.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQuery(name = "Shop.findByKey",
            query = "SELECT s FROM Shop s WHERE s.shopCode =:key")
public class Shop implements IShop{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shopCode;

    @Column(name = "manager", nullable = false)
    private String manager;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "shop")
    private List<Bicycle> bicycles;

    public int getCode() {return shopCode;}
    public void setCode(int code) {this.shopCode = code;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getManager() {return manager;}
    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}
}
