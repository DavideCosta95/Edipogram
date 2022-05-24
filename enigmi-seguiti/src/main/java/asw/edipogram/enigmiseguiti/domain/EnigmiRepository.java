package asw.edipogram.enigmiseguiti.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface EnigmiRepository extends CrudRepository<Enigma, Long> {

	Collection<Enigma> findAll();

	Collection<Enigma> findByAutore(String autore);

	Collection<Enigma> findByAutoreIn(Collection<String> autori);

	Collection<Enigma> findByTipo(String tipo);

	Collection<Enigma> findByTipoIn(Collection<String> tipi);

}
