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
import java.util.stream.Collectors;

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
	public Collection<GetEnigmaResponse> getEnigmiSeguiti(@PathVariable String utente) {
		Instant start = Instant.now();
		log.info("REST CALL: getEnigmiSeguiti {}", utente);
		Collection<EnigmaSeguito> enigmi = enigmiSeguitiService.getEnigmiSeguitiByUtente(utente);
		Duration duration = Duration.between(start, Instant.now());
		log.info("getEnigmiSeguiti {} (trovati {} enigmi in {} ms): " + enigmi, utente, enigmi.size(), duration.toMillis());
		return toEnigmiResponse(enigmi);
	}

	/* Converte una collezione di enigmi (in formato completo), in una collezione di enigmi (in formato breve). */
	private Collection<GetEnigmaResponse> toEnigmiResponse(Collection<EnigmaSeguito> enigmiSeguiti) {
		return enigmiSeguiti
				.stream()
				.map(GetEnigmaResponse::new)
				.collect(Collectors.toList());
	}
}
