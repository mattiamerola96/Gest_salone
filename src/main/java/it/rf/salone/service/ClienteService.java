package it.rf.salone.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;


import it.rf.salone.model.Cliente;
import it.rf.salone.repository.ClienteRepository;
import it.rf.salone.repository.OperatoreRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository vrepo;



    public Integer insertCliente(Cliente c) {
        Optional<Cliente> cliente = this.vrepo.findById(c.getCf());
        if (cliente.isPresent()) {
            return 0; // Utente già registrato
        } else {
            // Imposta il ruolo prima di salvare il cliente nel database
            c.setRuolo("cliente");
            this.vrepo.save(c);
            return 1; // Registrazione riuscita
        }
    }



    public ArrayList <Cliente> creaLista(){
        try {
        ArrayList <Cliente> l=(ArrayList<Cliente>) this.vrepo.findAll();
        return l;

        }catch(EmptyResultDataAccessException e) {
            return null;
        }
}


    public Cliente findById(String cf) {
        Optional<Cliente> c=this.vrepo.findById(cf);
        if(c.isPresent()) {


        Cliente cliente =c.get();
        return cliente;
    }else {
        return null;
    }
}


    public Cliente getClienteByUsernameAndPassword(String username, String password) {
        return vrepo.findByUsernameAndPassword(username, password);
    }



}