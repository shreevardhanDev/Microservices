package com.test.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repository.PaymentDao;
import com.test.payment.dto.PaymentRequest;
import com.test.payment.dto.PaymentResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentService {

	@Autowired
	private PaymentDao paymentDao;

	public PaymentResponse createPayment(PaymentRequest req) {
		PaymentResponse resp = new PaymentResponse();
		req.setPayment(paymentDao.save(req.getPayment()));
		log.info("saved payment in payment service");
		return resp;
	}

}
