package org.davideabbadessa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.davideabbadessa.entities.Location;

import java.util.List;

public class LocationDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U1-W3-D3-Jpa-Relationships");

    public void save(Location location) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(location);
        em.getTransaction().commit();
        em.close();
    }

    public Location findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Location location = em.find(Location.class, id);
        em.close();
        return location;
    }

    public List<Location> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Location> locations = em.createQuery("SELECT l FROM Location l", Location.class).getResultList();
        em.close();
        return locations;
    }

    public void update(Location location) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(location);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Location location = em.find(Location.class, id);
        if (location != null) {
            em.remove(location);
        }
        em.getTransaction().commit();
        em.close();
    }
}
