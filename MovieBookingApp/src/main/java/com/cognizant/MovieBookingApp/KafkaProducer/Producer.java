package com.cognizant.MovieBookingApp.KafkaProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	
	private static final String topic = "kafka_topic02";
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMsg(String msg) {
		kafkaTemplate.send(topic, msg);
	}

}
