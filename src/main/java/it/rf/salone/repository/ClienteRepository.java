package it.rf.salone.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.rf.salone.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>{

    Cliente findByUsernameAndPassword(String username, String password);

}