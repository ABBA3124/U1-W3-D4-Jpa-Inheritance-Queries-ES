package org.davideabbadessa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.davideabbadessa.entities.Persona;

import java.util.List;

public class PersonaDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U1-W3-D3-Jpa-Relationships");

    public void save(Persona persona) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(persona);
        em.getTransaction().commit();
        em.close();
    }

    public Persona findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Persona persona = em.find(Persona.class, id);
        em.close();
        return persona;
    }

    public List<Persona> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Persona> persone = em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
        em.close();
        return persone;
    }

    public void update(Persona persona) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(persona);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Persona persona = em.find(Persona.class, id);
        if (persona != null) {
            em.remove(persona);
        }
        em.getTransaction().commit();
        em.close();
    }
}
