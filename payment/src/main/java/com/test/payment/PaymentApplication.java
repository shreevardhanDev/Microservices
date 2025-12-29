package com.test.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableJpaRepositories(basePackages = "com.*")
@EntityScan(basePackages = "com.*")
//@EnableDiscoveryClient
@EnableWebMvc
@EnableAutoConfiguration(exclude = KafkaAutoConfiguration.class)
@SpringBootApplication
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

//	@Bean
//	PaymentConsumer getPaymentConsumer() {
//		return new PaymentConsumer();
//	}

}
