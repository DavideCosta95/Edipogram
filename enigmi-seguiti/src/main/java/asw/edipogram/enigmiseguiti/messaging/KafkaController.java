package asw.edipogram.enigmiseguiti.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaController {

	private final MessageConsumerFactory messageConsumerFactory;

	@Autowired
	private KafkaController(MessageConsumerFactory messageConsumerFactory) {
		this.messageConsumerFactory = messageConsumerFactory;
	}

	@KafkaListener(topics = "#{'${kafka.topics}'.split(',')}", groupId = "default")
	public void consumeMessage(@Payload String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
		log.info("Topic {} received message: {}", topic, message);
		MessageConsumer messageConsumer = messageConsumerFactory.getMessageConsumerByTopic(topic);
		messageConsumer.consumeMessage(message);
	}
}
