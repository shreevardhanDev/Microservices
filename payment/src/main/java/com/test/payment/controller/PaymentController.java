package com.test.payment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.payment.dto.PaymentRequest;
import com.test.payment.dto.PaymentResponse;
import com.test.payment.service.PaymentService;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

	@PostMapping(value = "/createPayment")
	public @ResponseBody PaymentResponse saveOrder(@RequestBody PaymentRequest req) {
		PaymentResponse resp = new PaymentResponse();
		LOGGER.info("got the request in payment controller");
		paymentService.createPayment(req);
		LOGGER.info("completed payment");
		
		return resp;
	}

}
