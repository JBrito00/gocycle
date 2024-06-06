package isel.sisinf.model.mappers;

import isel.sisinf.model.GpsDevice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class GpsDeviceDataMapper implements IDataMapper<GpsDevice> {
    private EntityManager entityManager;

    public GpsDeviceDataMapper() {
        this.entityManager = Persistence.createEntityManagerFactory("GpsSystem").createEntityManager();
    }

    public void create(GpsDevice gpsDevice) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(gpsDevice);
            entityManager.getTransaction().commit();
        } catch(Exception e){
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void update(GpsDevice gpsDevice) {
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(gpsDevice);
            entityManager.getTransaction().commit();
        } catch(Exception e){
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(int gpsDeviceId) {
        try {
            GpsDevice g = entityManager.find(GpsDevice.class, gpsDeviceId);
            if(g != null){
                entityManager.getTransaction().begin();
                entityManager.remove(g);
                entityManager.getTransaction().commit();
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
