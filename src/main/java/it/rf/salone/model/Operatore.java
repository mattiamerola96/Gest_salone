package it.rf.salone.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.time.LocalTime;


@Entity
@Table(name="operatore" )
public class Operatore {

    @Column
    private String nome;

    @Column
    private String cognome;

    @Id
    @Column
    private String cf;


    @Column
    private String ruolo;

    @Column
    private String username;

    @Column
    private String password;

    
    
    
    
    
    @ElementCollection
    @CollectionTable(name = "orari_disponibili", joinColumns = @JoinColumn(name = "operatore_cf"))
    private List<FasciaOraria> orariDisponibili;

    public List<FasciaOraria> getOrariDisponibili() {
        return orariDisponibili;
    }

    public void setOrariDisponibili(List<FasciaOraria> orariDisponibili) {
        this.orariDisponibili = orariDisponibili;
    }
    
    
    
    
    
    
    public String getOrariAsString() {
        StringBuilder orariString = new StringBuilder();
        for (FasciaOraria fascia : orariDisponibili) {
            orariString.append(fascia.getGiorno())
                       .append(": ")
                       .append(fascia.getOrarioApertura())
                       .append(" - ")
                       .append(fascia.getOrarioChiusura())
                       .append("<br>");
        }
        return orariString.toString();
    }
    
    public List<LocalTime> getOrariPerOra() {
        List<LocalTime> orariPerOra = new ArrayList<>();

        for (FasciaOraria fascia : orariDisponibili) {
            LocalTime orarioCorrente = fascia.getOrarioApertura();

            while (orarioCorrente.isBefore(fascia.getOrarioChiusura())) {
                orariPerOra.add(orarioCorrente);
                orarioCorrente = orarioCorrente.plusHours(1);
            }
        }

        return orariPerOra;
    }


    @OneToMany(mappedBy = "operatore")
    private List<Appuntamento> appuntamento;

    public void addAppuntamento(Appuntamento appuntamento) {
        this.appuntamento.add(appuntamento);
        appuntamento.setOperatore(this);
    }


        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getCognome() {
            return cognome;
        }

        public void setCognome(String cognome) {
            this.cognome = cognome;
        }

        public String getCf() {
            return cf;
        }

        public void setCf(String cf) {
            this.cf = cf;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }


        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }

public List<Appuntamento> getAppuntamento() {
    return appuntamento;
}
public void setAppuntamento(List<Appuntamento> appuntamento) {
    this.appuntamento = appuntamento;
}

public String getRuolo() {
    return ruolo;
}

public void setRuolo(String ruolo) {
    this.ruolo = ruolo;
}





}
