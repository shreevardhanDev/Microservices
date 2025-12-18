package com.test.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.payment.dto.PaymentRequest;
import com.test.payment.dto.PaymentResponse;
import com.test.payment.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/payment")
@Slf4j
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	@PostMapping(value = "/createPayment")
	public @ResponseBody PaymentResponse saveOrder(@RequestBody PaymentRequest req) {
		PaymentResponse resp = new PaymentResponse();
		resp = paymentService.createPayment(req);
		log.info("completed payment");
		return resp;
	}

}
