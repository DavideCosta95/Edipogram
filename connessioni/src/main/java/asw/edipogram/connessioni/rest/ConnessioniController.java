package asw.edipogram.connessioni.rest;

import asw.edipogram.connessioni.domain.*;

import asw.edipogram.connessioni.messaging.ConnessioneCreatedEvent;
import asw.edipogram.connessioni.messaging.KafkaController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@RestController
@Slf4j
public class ConnessioniController {

	private final ConnessioniService connessioniService;
	private final KafkaController kafkaController;

	@Autowired
	private ConnessioniController(ConnessioniService connessioniService, KafkaController kafkaController) {
		this.connessioniService = connessioniService;
		this.kafkaController = kafkaController;
	}

	/* Crea una nuova connessione. 
	* La richiesta contiene nel corpo una stringa della forma utente:tipo */ 
	@PostMapping("/connessioni")
	public Connessione createConnessione(@RequestBody CreateConnessioneRequest request) {
		String utente = request.getUtente();
		String tipo = request.getTipo();
		Connessione connessione = new Connessione(utente, tipo);
		connessione = connessioniService.addConnessione(connessione);
		log.info("REST CALL: createConnessione {}", connessione);
		kafkaController.sendMessage(new ConnessioneCreatedEvent(tipo, utente));
		return connessione;
	}	

	/* Trova tutte le connessioni. */ 
	@GetMapping("/connessioni")
	public Collection<Connessione> getConnessioni() {
		Collection<Connessione> connessioni;
		log.info("REST CALL: getConnessioni");
		connessioni = connessioniService.getConnessioni();
		log.info("REST CALL: getConnessioni: {}", connessioni);
		return connessioni;
	}

	/* Trova tutte le connessioni di un utente. */ 
	@GetMapping("/connessioni/{utente}")
	public Collection<Connessione> getConnessioni(@PathVariable String utente) {
		Collection<Connessione> connessioni;
		log.info("REST CALL: getConnessioni {}", utente);
		connessioni = connessioniService.getConnessioniByUtente(utente);
		log.info("REST CALL: getConnessioni {}: {}", utente, connessioni);
		return connessioni;
	}
}
