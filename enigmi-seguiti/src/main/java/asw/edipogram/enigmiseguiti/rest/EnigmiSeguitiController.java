package asw.edipogram.enigmiseguiti.rest;

import asw.edipogram.enigmiseguiti.domain.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant; 
import java.time.Duration;
import java.util.*;

@RestController
@Slf4j
public class EnigmiSeguitiController {

	private final EnigmiSeguitiService enigmiSeguitiService;

	@Autowired
	private EnigmiSeguitiController(EnigmiSeguitiService enigmiSeguitiService) {
		this.enigmiSeguitiService = enigmiSeguitiService;
	}

	/* Trova gli enigmi (in formato breve) degli utenti seguiti da utente. */ 
	@GetMapping("/enigmiseguiti/{utente}")
	public Collection<Enigma> getEnigmiSeguiti(@PathVariable String utente) {
		Instant start = Instant.now();
		log.info("REST CALL: getEnigmiSeguiti {}", utente);
		Collection<Enigma> enigmi = enigmiSeguitiService.getEnigmiSeguiti(utente); 
		Duration duration = Duration.between(start, Instant.now()); 
		log.info("getEnigmiSeguiti {} (trovati {} enigmi in {} ms): " + enigmi, utente, enigmi.size(), duration.toMillis());
		return enigmi; 
	}
}
