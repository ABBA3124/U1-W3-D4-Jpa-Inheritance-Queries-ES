package org.davideabbadessa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.davideabbadessa.entities.Concerto;
import org.davideabbadessa.entities.Evento;
import org.davideabbadessa.entities.PartitaDiCalcio;

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

    public List<Concerto> getConcertiInStreaming(boolean streaming) {
        EntityManager em = emf.createEntityManager();
        List<Concerto> concerti = em.createQuery("SELECT c FROM Concerto c WHERE c.streaming = :streaming", Concerto.class)
                .setParameter("streaming", streaming)
                .getResultList();
        em.close();
        return concerti;
    }

    public List<Concerto> getConcertiPerGenere(Concerto.Genere genere) {
        EntityManager em = emf.createEntityManager();
        List<Concerto> concerti = em.createQuery("SELECT c FROM Concerto c WHERE c.genere = :genere", Concerto.class)
                .setParameter("genere", genere)
                .getResultList();
        em.close();
        return concerti;
    }


    public List<PartitaDiCalcio> getPartiteVinteInCasa() {
        EntityManager em = emf.createEntityManager();
        List<PartitaDiCalcio> partiteVinteInCasa = em.createNamedQuery("PartitaDiCalcio.findPartiteVinteInCasa", PartitaDiCalcio.class).getResultList();
        em.close();
        return partiteVinteInCasa;
    }

    public List<PartitaDiCalcio> getPartiteVinteInTrasferta() {
        EntityManager em = emf.createEntityManager();
        List<PartitaDiCalcio> partiteVinteInTrasferta = em.createNamedQuery("PartitaDiCalcio.findPartiteVinteInTrasferta", PartitaDiCalcio.class).getResultList();
        em.close();
        return partiteVinteInTrasferta;
    }
}