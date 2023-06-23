package com.cognizant.UserManagement.KafkaConsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@KafkaListener(topics = "kafka_topic02", groupId = "group02")
	public void consumer(String msg) {
		System.out.println("Movie Booking application tracking: " + msg);
	}

}
