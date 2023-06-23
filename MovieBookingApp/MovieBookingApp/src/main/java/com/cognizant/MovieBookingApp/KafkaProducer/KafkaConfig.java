package com.cognizant.MovieBookingApp.KafkaProducer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

	@Bean
	public NewTopic newTopic() {
		return TopicBuilder.name("kafka_topic2").build();
	}	
	
//	@Bean
//	public KafkaAdmin kafkaAdmin() {
//		Map<String, Object> config = new HashMap<>();
//		config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, . . .);
//		return new KafkaAdmin(config);
//		
//	}

}
