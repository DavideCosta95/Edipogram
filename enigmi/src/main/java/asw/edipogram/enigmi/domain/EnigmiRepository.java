package asw.edipogram.enigmi.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.*; 

public interface EnigmiRepository extends CrudRepository<Enigma, Long> {

	Collection<Enigma> findAll();

	Collection<Enigma> findByAutore(String autore);

	Collection<Enigma> findByAutoreIn(Collection<String> autori);

	Collection<Enigma> findByTipo(String tipo);

	Collection<Enigma> findByTipoIn(Collection<String> tipi);

}

