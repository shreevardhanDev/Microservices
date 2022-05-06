package com.test.payment;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.test.order.dto.Payment;

@Configuration
public class KafkaConfiguration {

//	@Bean
//	public KafkaTemplate<String, Payment> producerTemplate() {
//		ProducerFactory<String, Payment> pf = new DefaultKafkaProducerFactory<>(producerProps());
//		return new KafkaTemplate<>(pf);
//	}
//
//	@Bean
//	public Map<String, Object> producerProps() {
//		Map<String, Object> props = new HashMap<>();
//		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//		props.put(ProducerConfig.RETRIES_CONFIG, 0);
//		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
//		props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
//		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
//		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//		return props;
//	}


	public ConsumerFactory<String, Payment> consumerFactory1() {
		JsonDeserializer<Payment> deserializer = new JsonDeserializer<>(Payment.class);

		Map<String, Object> props = new HashMap<>();
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer1");
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
	}
	

	public ConsumerFactory<String, Payment> consumerFactory2() {
		JsonDeserializer<Payment> deserializer = new JsonDeserializer<>(Payment.class);

		Map<String, Object> props = new HashMap<>();
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer2");
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
	}

	@Bean(name = "paymentFactory1")
	public ConcurrentKafkaListenerContainerFactory<String, Payment> kafkaListenerContainerFactory1() {
		ConcurrentKafkaListenerContainerFactory<String, Payment> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory1());
		return factory;
	}
	
	@Bean(name = "paymentFactory2")
	public ConcurrentKafkaListenerContainerFactory<String, Payment> kafkaListenerContainerFactory2() {
		ConcurrentKafkaListenerContainerFactory<String, Payment> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory2());
		return factory;
	}

}
