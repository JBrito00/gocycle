package isel.sisinf.model;

import jakarta.persistence.*;

import java.util.List;

public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(name = "manager", nullable = false)
    private String manager;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "locality", nullable = false)
    private String locality;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "shop")
    private List<Bicycle> bicycles;
}
