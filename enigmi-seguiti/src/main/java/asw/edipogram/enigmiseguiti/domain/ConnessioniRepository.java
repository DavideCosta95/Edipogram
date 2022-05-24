package asw.edipogram.enigmiseguiti.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ConnessioniRepository extends CrudRepository<Connessione, Long> {

	Collection<Connessione> findAll();

	Collection<Connessione> findByUtente(String utente);

}
