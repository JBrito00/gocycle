package isel.sisinf.model;

import jakarta.persistence.*;

import java.util.List;

public class GearSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int numberOgGears;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "gearSystem")
    private List<Bicycle> bicycles;


}
