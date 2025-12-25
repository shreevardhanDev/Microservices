package com.test.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repository.PaymentDao;
import com.test.payment.dto.PaymentRequest;
import com.test.payment.dto.PaymentResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentService {

	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	private ObjectMapper objectMapper;

    public String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Error converting object to JSON for Splunk: {}", e.getMessage());
            return "{ \"error\": \"Serialization failed\" }"; 
        }
    }

	public PaymentResponse createPayment(PaymentRequest req) {
		PaymentResponse resp = new PaymentResponse();
		req.setPayment(paymentDao.save(req.getPayment()));
		log.info(toJson(req.getPayment()));
		return resp;
	}

}
