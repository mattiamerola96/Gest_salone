package it.rf.salone.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="appuntamento")
public class Appuntamento {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private Integer numLavoro;

    @Column
    private int codiceOrdine;
    @Column
    private LocalDate dataAppuntamento;
   
    @Column
    private LocalTime orario;


    @ManyToOne
    private Operatore operatore;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Servizio servizio;

    private String codiceFiscaleCliente;

    public String getCodiceFiscaleCliente() {
        return codiceFiscaleCliente;
    }

    public void setCodiceFiscaleCliente(String codiceFiscaleCliente) {
        this.codiceFiscaleCliente = codiceFiscaleCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getCodiceOrdine() {
        return codiceOrdine;
    }

    public void setCodiceOrdine(int codiceOrdine) {
        this.codiceOrdine = codiceOrdine;
    }

    public LocalDate getDataAppuntamento() {
        return dataAppuntamento;
    }

    public void setDataAppuntamento(LocalDate dataAppuntamento ) {
        this.dataAppuntamento = dataAppuntamento;
    }

    public Integer getNumLavoro() {
        return numLavoro;
    }

    public void setNumLavoro(Integer numLavoro) {
        this.numLavoro = numLavoro;
    }

    public Operatore getOperatore() {
        return operatore;
    }

    public void setOperatore(Operatore operatore) {
        this.operatore = operatore;
    }

    public Servizio getServizio() {
        return servizio;
    }

    public void setServizio(Servizio servizio) {
        this.servizio = servizio;
    }

   

    public LocalTime getOrario() {
		return orario;
	}
    
    public void setOrario(LocalTime orario) {
		this.orario = orario;
	}

	public void setCodiceOrdine(String string) {
		// TODO Auto-generated method stub
		
	}
}