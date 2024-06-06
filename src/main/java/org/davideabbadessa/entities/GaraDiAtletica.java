package org.davideabbadessa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class GaraDiAtletica extends org.davideabbadessa.entities.Evento {
    @OneToMany
    private Set<Persona> atleti;

    @ManyToOne
    private Persona vincitore;

    public GaraDiAtletica() {
    }

    public GaraDiAtletica(Set<Persona> atleti, Persona vincitore) {
        this.atleti = atleti;
        this.vincitore = vincitore;
    }

    public Set<Persona> getAtleti() {
        return atleti;
    }

    public void setAtleti(Set<Persona> atleti) {
        this.atleti = atleti;
    }

    public Persona getVincitore() {
        return vincitore;
    }

    public void setVincitore(Persona vincitore) {
        this.vincitore = vincitore;
    }

    @Override
    public String toString() {
        return "GaraDiAtletica{" +
                "atleti=" + atleti +
                ", vincitore=" + vincitore +
                '}';
    }
}
