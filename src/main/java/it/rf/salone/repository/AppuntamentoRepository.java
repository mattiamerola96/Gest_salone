package it.rf.salone.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.rf.salone.model.Appuntamento;
import it.rf.salone.model.Cliente;

@Repository
public interface AppuntamentoRepository extends JpaRepository<Appuntamento, Integer> {

	@Query("INSERT into Appuntamento a (a.codiceOrdine, a.dataAppuntamento, a.orario, a.operatore, a.cliente, a.servizio) VALUES ( ?1, ?2, ?3, ?4, ?5, ?6)")
	Appuntamento save(Appuntamento appuntamento, Cliente cliente);
	
    List<Appuntamento> findByCliente(Cliente cliente);

	
	@Query("SELECT MAX(a.codiceOrdine) FROM Appuntamento a ")
    Integer findMaxCodiceOrdine();
	
	List<Appuntamento> findAll();

	List<Appuntamento> findByDataAppuntamentoBetween(LocalDate start, LocalDate end);

}
