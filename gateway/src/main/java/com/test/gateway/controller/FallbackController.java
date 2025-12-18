package com.test.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

	@GetMapping("/orderFallback")
	public Mono<String> orderServiceFallback() {
		return Mono.just("Order service not working");
	}

	@GetMapping("/paymentFallback")
	public Mono<String> paymentServiceFallback() {
		return Mono.just("Payment Service not working");
	}

	@GetMapping("/jwtAuthServiceFallback")
	public Mono<String> jwtAuthService() {
		return Mono.just("jwtAuthService Service not working");
	}

}
