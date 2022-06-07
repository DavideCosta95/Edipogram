package asw.edipogram.connessioni.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class ConnessioniService {

	private final ConnessioniRepository connessioniRepository;

	@Autowired
	private ConnessioniService(ConnessioniRepository connessioniRepository) {
		this.connessioniRepository = connessioniRepository;
	}

 	public Connessione addConnessione(Connessione connessione) {
		return connessioniRepository.save(connessione);
	}

 	public Connessione getConnessione(Long id) {
		return connessioniRepository.findById(id).orElse(null);
	}

 	public Collection<Connessione> getConnessioni() {
		return connessioniRepository.findAll();
	}

	public Collection<Connessione> getConnessioniByUtente(String utente) {
		return connessioniRepository.findByUtente(utente);
	}
}
