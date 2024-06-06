package org.davideabbadessa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Concerto extends org.davideabbadessa.entities.Evento {
    @Enumerated(EnumType.STRING)
    private Genere genere;
    private boolean streaming;

    public Concerto() {
    }

    public Concerto(Genere genere, boolean streaming) {
        this.genere = genere;
        this.streaming = streaming;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    public boolean isStreaming() {
        return streaming;
    }

    public void setStreaming(boolean streaming) {
        this.streaming = streaming;
    }

    @Override
    public String toString() {
        return "Concerto{" +
                "genere=" + genere +
                ", streaming=" + streaming +
                '}';
    }

    public enum Genere {CLASSICO, ROCK, POP}
}
