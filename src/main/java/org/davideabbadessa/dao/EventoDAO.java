package org.davideabbadessa.dao;

import davideabbadessa.entities.Evento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class EventoDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U1-W3-D3-Jpa-Relationships");

    public void save(Evento evento) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(evento);
        em.getTransaction().commit();
        em.close();
    }

    public Evento findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Evento evento = em.find(Evento.class, id);
        em.close();
        return evento;
    }

    public List<Evento> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Evento> eventi = em.createQuery("SELECT e FROM Evento e", Evento.class).getResultList();
        em.close();
        return eventi;
    }

    public void update(Evento evento) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(evento);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Evento evento = em.find(Evento.class, id);
        if (evento != null) {
            em.remove(evento);
        }
        em.getTransaction().commit();
        em.close();
    }
}
