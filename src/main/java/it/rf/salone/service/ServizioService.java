package it.rf.salone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import it.rf.salone.model.Servizio;
import it.rf.salone.repository.ServizioRepository;

@Service
public class ServizioService {

    public static List<Servizio> getServizi() {

        return null;
    }

    @Autowired
    public ServizioRepository srepo;


    public List<Servizio> getAllServizi() {
        return srepo.findAll();
    }

    public ArrayList <Servizio> creaLista(){
        try {
        ArrayList <Servizio> l=(ArrayList<Servizio>) this.srepo.findAll();
        return l;

        }catch(EmptyResultDataAccessException e) {
            return null;
        }
}

    public Servizio createServizio(Servizio nuovoServizio) {
        // Puoi eseguire ulteriori controlli prima di salvare il nuovo servizio
        return srepo.save(nuovoServizio);
    }





}