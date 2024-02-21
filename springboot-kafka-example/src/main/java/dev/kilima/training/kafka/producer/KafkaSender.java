package dev.kilima.training.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	String topic = "bundala";
	
	public void send(String message) {
		kafkaTemplate.send(topic, message);
	}
}
