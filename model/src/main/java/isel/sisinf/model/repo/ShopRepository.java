package isel.sisinf.model.repo;

import isel.sisinf.model.Shop;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ShopRepository implements IRepository<Shop, List<Shop>, Integer> {

    private EntityManager entityManager;
    private final ShopDataMapper mapper;

    public ShopRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.mapper = new ShopDataMapper();
    }

    @Override
    public Shop findByKey(Integer key) {
        try{
            return entityManager.find(Shop.class, key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Shop> find(String jpql, Object... params) {
        TypedQuery<Shop> query = entityManager.createQuery(jpql, Shop.class);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.getResultList();
    }
}
