package com.test.payment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.repository.PaymentDao;
import com.test.order.dto.Payment;
import com.test.payment.dto.PaymentRequest;
import com.test.payment.dto.PaymentResponse;

@Service
public class PaymentService {

	@Autowired
	private PaymentDao paymentDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

	@Transactional
	public void kafkaPaymentProcessing(Payment payment) {

		paymentDao.save(payment);
		LOGGER.info("payment Saved");

	}

	public PaymentResponse createPayment(PaymentRequest req) {
		PaymentResponse resp = new PaymentResponse();
		LOGGER.info(req.toString());
		this.processingPayment();
		resp.setPayment(req.getPayment());
		return resp;
	}

	private void processingPayment() {
		LOGGER.info("Processing payment");

		LOGGER.info("Payment Processed");
	}

}
