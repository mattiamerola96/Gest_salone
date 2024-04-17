package it.rf.salone.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rf.salone.model.Appuntamento;
import it.rf.salone.model.Cliente;
import it.rf.salone.repository.AppuntamentoRepository;
import jakarta.transaction.Transactional;

@Service
public class AppuntamentoService {

    @Autowired
    private AppuntamentoRepository appuntamentoRepository;

    public List<Appuntamento> getAllAppuntamenti() {
        return appuntamentoRepository.findAll();
    }

    @Transactional
    public void salvaAppuntamento(Appuntamento appuntamento, Cliente cliente) {
        // Imposta il cliente associato all'appuntamento
        appuntamento.setCliente(cliente);
        // Salva l'appuntamento nel repository
        appuntamentoRepository.save(appuntamento);
    }
    
    public List<Appuntamento> getPrenotazioniByCliente(Cliente cliente) {
        // Utilizza il repository per ottenere le prenotazioni del cliente
        return appuntamentoRepository.findByCliente(cliente);
    }

    public List<Appuntamento> getAppuntamentiPerSettimana(LocalDate inizioSettimana, LocalDate fineSettimana) {
        // Ottieni la data di inizio della settimana corrente
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);

        // Ottieni la data di fine della settimana corrente
        LocalDate endOfWeek = startOfWeek.plusDays(6);

        // Recupera gli appuntamenti compresi tra la data di inizio e la data di fine della settimana
        return appuntamentoRepository.findByDataAppuntamentoBetween(startOfWeek, endOfWeek);
    }

}