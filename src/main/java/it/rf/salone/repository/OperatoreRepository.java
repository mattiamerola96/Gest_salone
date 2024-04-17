package it.rf.salone.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.rf.salone.model.Operatore;

@Repository
public interface OperatoreRepository extends JpaRepository<Operatore, String>{

	 @Query("SELECT o FROM Operatore o WHERE o.cf=?1 ")
	 Optional<Operatore> findByCf(String cf);
	 
	
    

    Operatore getOperatoreByCf(String cf);

    List<Operatore> findByRuolo(String ruolo);
    
    List<Operatore> findAll();

    Operatore findByUsernameAndPassword(String username, String password);
    
    Operatore findByUsername(String username);

}