package it.rf.salone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalTime;

@Embeddable
public class FasciaOraria {

    @Column
    private String giorno;

    @Column
    private LocalTime orarioApertura;

    @Column
    private LocalTime orarioChiusura;

	public String getGiorno() {
		return giorno;
	}

	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}

	public LocalTime getOrarioApertura() {
		return orarioApertura;
	}

	public void setOrarioApertura(LocalTime orarioApertura) {
		this.orarioApertura = orarioApertura;
	}

	public LocalTime getOrarioChiusura() {
		return orarioChiusura;
	}

	public void setOrarioChiusura(LocalTime orarioChiusura) {
		this.orarioChiusura = orarioChiusura;
	}

	public FasciaOraria() {
        
    }

	
    
	
}
