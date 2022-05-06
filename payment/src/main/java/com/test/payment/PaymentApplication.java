package com.test.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.kafka.consumers.PaymentConsumer;

@EnableJpaRepositories(basePackages = "com.*")
@EntityScan(basePackages = "com.*")
@EnableEurekaClient
@EnableWebMvc
@EnableKafka
@SpringBootApplication
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

	@Bean
	PaymentConsumer getPaymentConsumer() {
		return new PaymentConsumer();
	}

}
