package asw.edipogram.connessioni.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.*; 

public interface ConnessioniRepository extends CrudRepository<Connessione, Long> {

	Collection<Connessione> findAll();

	Collection<Connessione> findByUtente(String utente);

}

