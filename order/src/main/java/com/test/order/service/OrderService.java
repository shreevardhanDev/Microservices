package com.test.order.service;

import java.net.URI;
import java.net.URISyntaxException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repository.OrderDao;
import com.test.order.dto.Order;
import com.test.order.dto.OrderRequest;
import com.test.order.dto.OrderResponse;
import com.test.order.dto.Payment;
import com.test.order.dto.PaymentRequest;
import com.test.order.dto.PaymentResponse;
import com.test.order.listners.AuditListener;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@NoArgsConstructor
@RequiredArgsConstructor(onConstructor =@__(@Autowired))
public class OrderService {

    @NonNull
    private RestTemplate template;

    @NonNull
    private OrderDao orderDao;

    @Value("${com.test.payment.controller}")
    private String paymentRest;
    
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

    //	@Autowired
    //	private KafkaTemplate<String, Payment> kafkaTemplate;

    //	@RateLimiter()
    //	@Bulkhead(name = )
    //	@TimeLimiter(name = "wow", fallbackMethod = "wow")
    //	@CircuitBreaker(name = "paymentService", fallbackMethod = "fallbackMethodForPayment")
    //	@Retry(name = "paymentService", fallbackMethod = "fallbackMethodForPayment")

    @Transactional
    public OrderResponse createOrder(OrderRequest order) {
        OrderResponse resp = new OrderResponse();
        Order or = orderDao.save(order.getOrder());
        log.info(toJson(order));
        PaymentRequest req = new PaymentRequest();
        req.setPayment(order.getPayment());
        Payment pay = this.doPayment(req);
        resp.setPayment(pay);
        resp.setOrder(or);
        return resp;
    }

    private Payment doPayment(PaymentRequest request) {
        String url = this.paymentRest + "/createPayment";
        Payment pBody = paymentMethod(request, url);
        return pBody;
    }

    private Payment paymentMethod(PaymentRequest request, String url) {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + AuditListener.getJsonWebTocken());

        RequestEntity<PaymentRequest> req = new RequestEntity<PaymentRequest>(request, headers, HttpMethod.POST, getUri(url));

        ResponseEntity<PaymentResponse> pResp = template.exchange(req, PaymentResponse.class);
        Payment pBody = pResp.getBody().getPayment();
        return pBody;
    }

    private URI getUri(String url) {
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            log.error("issue with url", e);
        }
        return uri;
    }

    //	public OrderResponse fallbackMethodForPayment(OrderRequest request, Throwable t) {
    //
    //		log.info("Fall Back Method for payment");
    //		OrderResponse resp = new OrderResponse();
    //		Order or = new Order();
    //		or.setDescription("Fall Back Method");
    //		resp.setOrder(or);
    //		return resp;
    //	}

}
