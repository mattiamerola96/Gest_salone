package it.rf.salone.model;

import java.time.Duration;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "servizio")
public class Servizio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Usa l'auto-incremento
    @Column(name = "idServizio")
    private int idServizio;

    @Column
    private String nomeServizio;

    @Column
    private float prezzo;

    @OneToMany(mappedBy = "servizio")
    private List<Appuntamento> appuntamento;

    

   

	public int getIdServizio() {
        return idServizio;
    }

    public void setIdServizio(int idServizio) {
        this.idServizio = idServizio;
    }

    public String getNomeServizio() {
        return nomeServizio;
    }

    public void setNomeServizio(String nomeServizio) {
        this.nomeServizio = nomeServizio;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }
    
    
}