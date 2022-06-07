package asw.edipogram.enigmiseguiti.messaging;

import asw.edipogram.enigmiseguiti.domain.Connessione;
import asw.edipogram.enigmiseguiti.domain.ConnessioniService;
import asw.edipogram.enigmiseguiti.domain.Enigma;
import asw.edipogram.enigmiseguiti.domain.EnigmiService;
import asw.edipogram.enigmiseguiti.utils.EnigmaModelConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumerFactory {

	private final ConnessioniService connessioniService;
	private final EnigmiService enigmiService;
	private final EnigmaModelConverter enigmaModelConverter;

	@Autowired
	private MessageConsumerFactory(ConnessioniService connessioniService, EnigmiService enigmiService, EnigmaModelConverter enigmaModelConverter) {
		this.connessioniService = connessioniService;
		this.enigmiService = enigmiService;
		this.enigmaModelConverter = enigmaModelConverter;
	}

	public MessageConsumer getMessageConsumerByTopic(String topic) {
		switch (topic) {
			case "enigmi":
				return message -> {
					ObjectMapper objectMapper = new ObjectMapper();
					EnigmaCreatedEvent enigmaCreatedEvent;
					try {
						enigmaCreatedEvent = objectMapper.readValue(message, EnigmaCreatedEvent.class);
					} catch (JsonProcessingException e) {
						log.error("Error while parsing as Enigma string: {}", message);
						return;
					}
					Enigma enigma = enigmaModelConverter.toEnigma(enigmaCreatedEvent);
					enigma = enigmiService.addEnigma(enigma);
					log.info("Add enigma: {}", enigma);
				};
			case "connessioni":
				return message -> {
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
				};
			default:
				log.warn("Message type not recognized");
				return message -> {};
		}
	}
}
