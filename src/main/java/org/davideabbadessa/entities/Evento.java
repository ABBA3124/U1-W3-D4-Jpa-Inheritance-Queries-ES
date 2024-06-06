package org.davideabbadessa.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Event")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private EventType eventType;

    @Column(name = "max_participants", nullable = false)
    private int maxParticipants;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<Partecipazione> listaPartecipazioni;

    public Evento() {
    }

    public Evento(List<Partecipazione> listaPartecipazioni, Location location, int maxParticipants, EventType eventType, String description, LocalDate eventDate, String title) {
        this.listaPartecipazioni = listaPartecipazioni;
        this.location = location;
        this.maxParticipants = maxParticipants;
        this.eventType = eventType;
        this.description = description;
        this.eventDate = eventDate;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Partecipazione> getListaPartecipazioni() {
        return listaPartecipazioni;
    }

    public void setListaPartecipazioni(List<Partecipazione> listaPartecipazioni) {
        this.listaPartecipazioni = listaPartecipazioni;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", eventDate=" + eventDate +
                ", description='" + description + '\'' +
                ", eventType=" + eventType +
                ", maxParticipants=" + maxParticipants +
                ", location=" + location +
                ", listaPartecipazioni=" + listaPartecipazioni +
                '}';
    }

    public enum EventType {
        PRIVATO, PUBBLICO
    }
}