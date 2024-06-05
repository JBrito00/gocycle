package isel.sisinf.jpa;

import isel.sisinf.jpa.genericInterfaces.IRepository;
import isel.sisinf.model.GpsDevice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class GpsDeviceRepository implements IRepository<GpsDevice, List<GpsDevice>, Integer> {

    private EntityManager entityManager;
    private final GpsDeviceDataMapper mapper;

    public GpsDeviceRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.mapper = new GpsDeviceDataMapper();
    }

    @Override
    public GpsDevice findByKey(Integer key) {
        try{
            return entityManager.find(GpsDevice.class, key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<GpsDevice> find(String jpql, Object... params) {
        TypedQuery<GpsDevice> query = entityManager.createQuery(jpql, GpsDevice.class);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.getResultList();
    }
}
