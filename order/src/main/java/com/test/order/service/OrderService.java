package com.test.order.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.repository.OrderDao;
import com.test.order.dto.Order;
import com.test.order.dto.OrderRequest;
import com.test.order.dto.OrderResponse;
import com.test.order.dto.Payment;
import com.test.order.dto.PaymentRequest;
import com.test.order.dto.PaymentResponse;
import com.test.order.listners.AuditListener;

@Service
public class OrderService {

	@Autowired
	private RestTemplate template;

	@Autowired
	private KafkaTemplate<String, Payment> kafkaTemplate;

	@Autowired
	private OrderDao orderDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

	private final String paymentService = "http://PAYMENT-SERVICE/payment";

//	@RateLimiter()
//	@Bulkhead(name = )
//	@TimeLimiter(name = "wow", fallbackMethod = "wow")
//	@CircuitBreaker(name = "paymentService", fallbackMethod = "fallbackMethodForPayment")
//	@Retry(name = "paymentService", fallbackMethod = "fallbackMethodForPayment")
	
	
	@Transactional
	public OrderResponse createOrder(OrderRequest order) {
		OrderResponse resp = new OrderResponse();
		Order or = orderDao.save(order.getOrder());
		LOGGER.info( order.getPayment().toString());
		kafkaTemplate.send("payment", order.getPayment());
		resp.setOrder(or);
		return resp;
	}

	public Payment doPayment(PaymentRequest request) {
		String url = this.paymentService + "/createPayment";
		LOGGER.info("Calling PaymentService");

		Payment pBody = paymentMethod(request, url);
		return pBody;
	}

	private Payment paymentMethod(PaymentRequest request, String url) {
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + AuditListener.getJsonWebTocken());
		URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		RequestEntity<PaymentRequest> req = new RequestEntity<PaymentRequest>(request, headers, HttpMethod.POST, uri);

		ResponseEntity<PaymentResponse> pResp = template.exchange(req, PaymentResponse.class);
		LOGGER.info("Got Response from PaymentService");
		Payment pBody = pResp.getBody().getPayment();
		return pBody;
	}

	public OrderResponse fallbackMethodForPayment(OrderRequest request, Throwable t) {

		LOGGER.info("Fall Back Method for payment");
		OrderResponse resp = new OrderResponse();
		Order or = new Order();
		or.setDescription("Fall Back Method");
		resp.setOrder(or);
		return resp;
	}

}
