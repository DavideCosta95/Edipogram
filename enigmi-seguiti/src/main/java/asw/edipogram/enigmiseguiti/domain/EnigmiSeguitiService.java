package asw.edipogram.enigmiseguiti.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class EnigmiSeguitiService {
	private final EnigmiSeguitiRepository enigmiSeguitiRepository;

	@Autowired
	private EnigmiSeguitiService(EnigmiSeguitiRepository enigmiSeguitiRepository) {
		this.enigmiSeguitiRepository = enigmiSeguitiRepository;

	}

	public Collection<EnigmaSeguito> getEnigmiSeguitiByUtente(String utente) {
		return enigmiSeguitiRepository.findByUtenteOrderByIdEnigma(utente);
	}
}
