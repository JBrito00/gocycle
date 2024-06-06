package isel.sisinf.model.repo;

import isel.sisinf.model.mappers.BicycleDataMapper;
import isel.sisinf.model.Bicycle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.text.Bidi;
import java.util.List;

public class BicycleRepository implements IRepository<Bicycle, List<Bicycle>, Integer> {

    private EntityManager entityManager;
    private final BicycleDataMapper mapper;

    public BicycleRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.mapper = new BicycleDataMapper();
    }

    @Override
    public Bicycle findByKey(Integer key) {
        try{
            return entityManager.find(Bicycle.class, key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Bicycle> find(String jpql, Object... params) {
        TypedQuery<Bicycle> query = entityManager.createQuery(jpql, Bicycle.class);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.getResultList();
    }

    @Override
    public void add(Bicycle bicycle) {
        BicycleDataMapper b = new BicycleDataMapper();
        try{
            b.create(bicycle);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(Integer bikeId) {
        BicycleDataMapper b = new BicycleDataMapper();
        try{
            b.delete(bikeId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}