package org.davideabbadessa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.davideabbadessa.entities.Partecipazione;

import java.util.List;

public class PartecipazioneDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U1-W3-D3-Jpa-Relationships");

    public void save(Partecipazione partecipazione) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(partecipazione);
        em.getTransaction().commit();
        em.close();
    }

    public Partecipazione findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Partecipazione partecipazione = em.find(Partecipazione.class, id);
        em.close();
        return partecipazione;
    }

    public List<Partecipazione> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Partecipazione> partecipazioni = em.createQuery("SELECT p FROM Partecipazione p", Partecipazione.class).getResultList();
        em.close();
        return partecipazioni;
    }

    public void update(Partecipazione partecipazione) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(partecipazione);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Partecipazione partecipazione = em.find(Partecipazione.class, id);
        if (partecipazione != null) {
            em.remove(partecipazione);
        }
        em.getTransaction().commit();
        em.close();
    }
}
