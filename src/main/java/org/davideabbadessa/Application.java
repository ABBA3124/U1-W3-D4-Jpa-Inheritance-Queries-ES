package org.davideabbadessa;

import davideabbadessa.entities.Evento;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.davideabbadessa.dao.EventoDAO;
import org.davideabbadessa.dao.LocationDAO;
import org.davideabbadessa.dao.PartecipazioneDAO;
import org.davideabbadessa.dao.PersonaDAO;
import org.davideabbadessa.entities.Location;
import org.davideabbadessa.entities.Partecipazione;
import org.davideabbadessa.entities.Persona;

import java.time.LocalDate;
import java.util.ArrayList;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U1-W3-D3-Jpa-Relationships");

    public static void main(String[] args) {

        System.out.println("Start U1-W3-D3-Jpa-Relationships-ES");


        // CREO ISTANZE DAO PER OPERAZIONI DI PERSISTENZA
        PersonaDAO personaDAO = new PersonaDAO();
        LocationDAO locationDAO = new LocationDAO();
        EventoDAO eventoDAO = new EventoDAO();
        PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO();


        //CREO ENTITà  E LE SALVO

        //PERSONA
        Persona persona = new Persona("Alessio", "Vulpinari", "alessiovulpinari@gmail.com", LocalDate.of(1999, 7, 3), 'F', new ArrayList<>());

        personaDAO.save(persona);
        System.out.println("User: " + persona.getNome() + " " + persona.getCognome() + " Aggiunto/a con successo");

        //LOCATION
        Location location = new Location("Sagra della salsiccia", "Rimini");

        locationDAO.save(location);
        System.out.println(" " + persona.getNome() + " " + persona.getCognome() + " Aggiunto/a con successo all'evento: " + location.getNome() + " Che si terrà: " + location.getCitta());

        //EVENTO
        davideabbadessa.entities.Evento evento = new Evento(new ArrayList<>(), location, 53241, Evento.EventType.PUBBLICO, "Carnevale", LocalDate.of(2024, 10, 12), "Salsiccia a volontà");

        eventoDAO.save(evento);
        System.out.println("Evento creato con successo !");

        //CREO LA PARTECIPAZIONE E LA SALVO
        Partecipazione partecipazione = new Partecipazione(persona, evento, Partecipazione.Stato.DA_CONFERMARE);

        partecipazioneDAO.save(partecipazione);

        //REFRESH LISTE PARTECIPAZIONE
        persona.getListaPartecipazioni().add(partecipazione);
        evento.getListaPartecipazioni().add(partecipazione);

        //AGGIORNO ENTITà NEL DB
        personaDAO.update(persona);
        eventoDAO.update(evento);

        System.out.println("User: " + persona.getNome() + " " + persona.getCognome() + " " + "Location: " + "Evento: " + evento.getTitle() + " Partecipazione sono stati salvati con successo dentro il DB. ");


    }
}
