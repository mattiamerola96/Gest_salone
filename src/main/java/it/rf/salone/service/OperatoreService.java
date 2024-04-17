package it.rf.salone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import it.rf.salone.model.Cliente;
import it.rf.salone.model.Operatore;
import it.rf.salone.repository.OperatoreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OperatoreService {

    @Autowired
    private OperatoreRepository operatoreRepository;

    public List<Operatore> getOperatoriByRuolo(String ruolo) {
        return operatoreRepository.findByRuolo(ruolo);
    }

    public List<Operatore> getOperatori() {
        return operatoreRepository.findAll();
    }

    public Operatore getOperatoreByUsernameAndPassword(String username, String password) {
        return operatoreRepository.findByUsernameAndPassword(username, password);
    }

    public Operatore getOperatoreByUsername(String username) {
    	return operatoreRepository.findByUsername(username);
    }

   



    public ArrayList <Operatore> creaLista(){
        try {
        ArrayList <Operatore> l=(ArrayList<Operatore>) this.operatoreRepository.findAll();
        return l;

        }catch(EmptyResultDataAccessException e) {
            return null;
        }
}


    public Operatore findById(String cf) {
        Optional<Operatore> o=this.operatoreRepository.findById(cf);
        if(o.isPresent()) {


        Operatore operatore =o.get();
        return operatore;
    }else {
        return null;
    }
}
    
    public void salvaOperatore(Operatore operatore) {
        // Puoi implementare logiche aggiuntive qui prima di salvare l'operatore, se necessario
        operatoreRepository.save(operatore);
    }


    public Integer insertOperatore(Operatore o) {
        Optional<Operatore> operatore = this.operatoreRepository.findById(o.getCf());
        if (operatore.isPresent()) {
            return 0; // Utente giÃ  registrato
        } else {
            // Imposta il ruolo prima di salvare l'operatore nel database
            o.setRuolo("operatore");
            this.operatoreRepository.save(o);
            return 1; // Registrazione riuscita
        }
    }

    public Optional<Operatore> getOperatoreByCf(String operatoreCf) {
        return operatoreRepository.findByCf(operatoreCf);
    }

}
