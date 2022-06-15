package asw.edipogram.enigmiseguiti.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface EnigmiSeguitiRepository extends CrudRepository<EnigmaSeguito, EnigmaSeguito.EnigmaSeguitoKey> {

	Collection<EnigmaSeguito> findByUtente(String utente);
}
