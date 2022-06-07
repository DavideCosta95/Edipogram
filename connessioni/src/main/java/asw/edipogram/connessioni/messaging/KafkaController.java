package asw.edipogram.connessioni.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class KafkaController {

	private final KafkaTemplate<String, ConnessioneCreatedEvent> kafkaTemplate;

	@Value(value = "${kafka.topic}")
	private String topicName;

	@Autowired
	private KafkaController(KafkaTemplate<String, ConnessioneCreatedEvent> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(ConnessioneCreatedEvent message) {
		ListenableFuture<SendResult<String, ConnessioneCreatedEvent>> future = kafkaTemplate.send(topicName, message);
		future.addCallback(new ListenableFutureCallback<>() {
			@Override
			public void onSuccess(SendResult<String, ConnessioneCreatedEvent> result) {
				log.info("Sent message=[{}] with offset=[{}]", message, result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("Unable to send message=[{}]", message, ex);
			}
		});
	}
}
