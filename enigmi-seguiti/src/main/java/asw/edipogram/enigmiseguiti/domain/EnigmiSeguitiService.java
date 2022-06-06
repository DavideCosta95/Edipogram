package asw.edipogram.enigmiseguiti.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.*;

@Service
public class EnigmiSeguitiService {

	private final ConnessioniService connessioniService;
	private final EnigmiService enigmiService;

	@Autowired
	private EnigmiSeguitiService(ConnessioniService connessioniService, EnigmiService enigmiService) {
		this.connessioniService = connessioniService;
		this.enigmiService = enigmiService;
	}

	public Collection<Enigma> getEnigmiSeguiti(String utente) {
		Collection<Enigma> enigmiSeguiti = new TreeSet<>();
		Collection<Connessione> connessioni = connessioniService.getConnessioniByUtente(utente);
		Collection<String> tipiSeguiti =
				connessioni
						.stream()
						.map(Connessione::getTipo)
						.collect(Collectors.toSet());
		if (!tipiSeguiti.isEmpty()) {
			Collection<Enigma> enigmi = enigmiService.getEnigmiByTipi(tipiSeguiti);
			enigmiSeguiti.addAll(enigmi);
		}
		return enigmiSeguiti;
	}
}
