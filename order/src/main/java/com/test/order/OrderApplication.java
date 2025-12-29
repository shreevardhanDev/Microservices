package com.test.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.web.client.RestTemplate;

@EnableJpaRepositories(basePackages = "com.*")
@EntityScan(basePackages = "com.*")
//@EnableDiscoveryClient
@SpringBootApplication
//@EnableAutoConfiguration(exclude = KafkaAutoConfiguration.class)
//@EnableKafka
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            
            if (authentication != null && authentication.getCredentials() instanceof AbstractOAuth2Token) {
                String tokenValue = ((AbstractOAuth2Token) authentication.getCredentials()).getTokenValue();
                request.getHeaders().setBearerAuth(tokenValue);
            }
            return execution.execute(request, body);
        });
        return restTemplate;
    }

}
