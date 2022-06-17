package asw.edipogram.enigmiseguiti.messaging;

import asw.edipogram.enigmiseguiti.domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class MessageConsumerFactory {

	private final ConnessioniService connessioniService;
	private final EnigmiService enigmiService;

	private final Map<String, MessageConsumer> topicName2messageConsumeStrategy;

	@Autowired
	private MessageConsumerFactory(ConnessioniService connessioniService, EnigmiService enigmiService) {
		this.connessioniService = connessioniService;
		this.enigmiService = enigmiService;
		this.topicName2messageConsumeStrategy = getMessageConsumersMap();
	}

	private Map<String, MessageConsumer> getMessageConsumersMap() {
		return Map.of(
				"enigmi",
				message -> {
					ObjectMapper objectMapper = new ObjectMapper();
					EnigmaCreatedEvent enigmaCreatedEvent;
					try {
						enigmaCreatedEvent = objectMapper.readValue(message, EnigmaCreatedEvent.class);
					} catch (JsonProcessingException e) {
						log.error("Error while parsing as Enigma string: {}", message);
						return;
					}
					Enigma enigma = enigmiService.addEnigma(toEnigma(enigmaCreatedEvent));
					log.info("Add enigma: {}", enigma);
				},
				"connessioni",
				message -> {
					ObjectMapper objectMapper = new ObjectMapper();
					ConnessioneCreatedEvent connessioneCreatedEvent;
					try {
						connessioneCreatedEvent = objectMapper.readValue(message, ConnessioneCreatedEvent.class);
					} catch (JsonProcessingException e) {
						log.error("Error while parsing as Connessione string: {}", message);
						return;
					}
					Connessione connessione = new Connessione(connessioneCreatedEvent.getUtente(), connessioneCreatedEvent.getTipo());
					connessione = connessioniService.addConnessione(connessione);
					log.info("Add connessione: {}", connessione);
				}
		);
	}

	public MessageConsumer getMessageConsumerByTopic(String topic) {
		return topicName2messageConsumeStrategy.getOrDefault(topic, message -> log.warn("Message type not recognized"));
	}

	public Enigma toEnigma(EnigmaCreatedEvent enigmaCreatedEvent) {
		String autore = enigmaCreatedEvent.getAutore();
		String tipo = enigmaCreatedEvent.getTipo();
		String tipoSpecifico = enigmaCreatedEvent.getTipoSpecifico();
		String titolo = enigmaCreatedEvent.getTitolo();
		String[] testo = enigmaCreatedEvent.getTesto();
		return new Enigma(autore, tipo, tipoSpecifico, titolo, testo);
	}
}
