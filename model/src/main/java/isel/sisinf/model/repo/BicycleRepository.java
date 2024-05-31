package isel.sisinf.model.repo;

import isel.sisinf.model.Bicycle;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BicycleRepository implements IRepository<Bicycle, int>{

    private final EntityManager em;
    private final BicycleDataMapper mapper;

    public BicycleRepository(EntityManager em){
        this.em = em;
        this.mapper = new BicycleDataMapper();
    }

    @Override
    public Bicycle findById(int id) {
        BicycleDataMapper b = new BicycleDataMapper();
        try{
            return b.read(id);
        }
    }

    @Override
    public String find() {
        return null;
    }

    @Override
    public void add(Object o) {}

    @Override
    public void add() {

    }

    @Override
    public void delete(Object o) {

    }

    @Override
    public void save(Object o) {

    }

    @Override
    public List<String> findAll() {
        return List.of();
    }
}
