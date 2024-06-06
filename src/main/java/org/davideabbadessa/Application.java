package org.davideabbadessa;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.davideabbadessa.dao.EventoDAO;
import org.davideabbadessa.dao.LocationDAO;
import org.davideabbadessa.dao.PartecipazioneDAO;
import org.davideabbadessa.dao.PersonaDAO;
import org.davideabbadessa.entities.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U1-W3-D3-Jpa-Relationships");

    public static void main(String[] args) {

        System.out.println("Start U1-W3-D3-Jpa-Relationships-ES");


        // CREO ISTANZE DAO PER OPERAZIONI DI PERSISTENZA
        PersonaDAO personaDAO = new PersonaDAO();
        LocationDAO locationDAO = new LocationDAO();
        EventoDAO eventoDAO = new EventoDAO();
        PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO();


        // PERSONA
        Persona persona = new Persona("Alessio", "Vulpinari", "alessiovulpinari@gmail.com", LocalDate.of(1999, 7, 3), 'F', new ArrayList<>());
        personaDAO.save(persona);
        System.out.println("User: " + persona.getNome() + " " + persona.getCognome() + " aggiunto/a con successo");

        // LOCATION
        Location location = new Location("Sagra della salsiccia", "Rimini");
        locationDAO.save(location);
        System.out.println(persona.getNome() + " " + persona.getCognome() + " aggiunto/a con successo all'evento: " + location.getNome() + " che si terrà: " + location.getCitta());

        // EVENTO - Partita Di Calcio
        PartitaDiCalcio partitaDiCalcio = new PartitaDiCalcio("Squadra A", "Squadra B", "Squadra A", 2, 1);
        partitaDiCalcio.setLocation(location);
        partitaDiCalcio.setEventDate(LocalDate.of(2024, 10, 12));
        partitaDiCalcio.setTitle("Partita di Calcio");
        partitaDiCalcio.setEventType(Evento.EventType.PUBBLICO);
        partitaDiCalcio.setMaxParticipants(50000);
        eventoDAO.save(partitaDiCalcio);
        System.out.println("Partita di calcio creata con successo!");

        // EVENTO - Gara Di Atletica
        GaraDiAtletica garaDiAtletica = new GaraDiAtletica(new HashSet<>(), persona);
        garaDiAtletica.setLocation(location);
        garaDiAtletica.setEventDate(LocalDate.of(2024, 11, 1));
        garaDiAtletica.setTitle("Gara di Atletica");
        garaDiAtletica.setEventType(Evento.EventType.PUBBLICO);
        garaDiAtletica.setMaxParticipants(100);
        eventoDAO.save(garaDiAtletica);
        System.out.println("Gara di atletica creata con successo!");

        // EVENTO - Concerto
        Concerto concerto = new Concerto(Concerto.Genere.ROCK, true);
        concerto.setLocation(location);
        concerto.setEventDate(LocalDate.of(2024, 12, 25));
        concerto.setTitle("Concerto di Natale");
        concerto.setEventType(Evento.EventType.PUBBLICO);
        concerto.setMaxParticipants(2000);
        eventoDAO.save(concerto);
        System.out.println("Concerto creato con successo!");

        // CREO LA PARTECIPAZIONE E LA SALVO
        Partecipazione partecipazione = new Partecipazione(persona, partitaDiCalcio, Partecipazione.Stato.DA_CONFERMARE);
        partecipazioneDAO.save(partecipazione);


        // AGGIORNO ENTITÀ NEL DB
        personaDAO.update(persona);
        eventoDAO.update(partitaDiCalcio);

        System.out.println("User: " + persona.getNome() + " " + persona.getCognome() + " " + "Location: " + "Evento: " + partitaDiCalcio.getTitle() + " Partecipazione sono stati salvati con successo nel DB.");

        // Named Queries JPA
        List<PartitaDiCalcio> partiteVinteInCasa = eventoDAO.getPartiteVinteInCasa();
        System.out.println("Partite vinte in casa: " + partiteVinteInCasa.size());

        List<PartitaDiCalcio> partiteVinteInTrasferta = eventoDAO.getPartiteVinteInTrasferta();
        System.out.println("Partite vinte in trasferta: " + partiteVinteInTrasferta.size());

    }
}

