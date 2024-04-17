package it.rf.salone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.rf.salone.model.Servizio;

public interface ServizioRepository  extends JpaRepository<Servizio, Integer> {

	Optional<Servizio> findById(Integer idServizio);

	
	
}
