package com.test.order.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.repository.OrderDao;
import com.test.order.dto.Order;
import com.test.order.dto.OrderRequest;
import com.test.order.dto.OrderResponse;
import com.test.order.dto.Payment;
import com.test.order.dto.PaymentResponse;
import com.test.order.dto.ThreadLocalObject;
import com.test.order.listners.AuditListener;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK, properties = { "spring.cloud.config.enabled=false",
		"eureka.client.enabled=false" })
@ExtendWith(value = PostgreSQLMock.class)
public class OrderServiceIT {

	@SpyBean
	private RestTemplate restTemplate;

	@SpyBean
	private OrderDao orderDao;

	@Autowired
	private OrderService orderService;

	private ResponseEntity<PaymentResponse> getMockPaymentResponse() {
		Payment payment = Payment.builder().amount(20.2).transctionId(31L).id(100L).build();
		PaymentResponse response = PaymentResponse.builder().payment(payment).build();
		return new ResponseEntity<PaymentResponse>(response, HttpStatus.OK);

	}

	@BeforeEach
	void before() {
		ThreadLocalObject obj = new ThreadLocalObject();
		obj.setJsonTocken("MockJsonTocken");
		AuditListener.setAuditUserDetail(obj);
	}

	@Test
	void test_order_create() {
		PostgreSQLMock.runScript("initial_script.sql");
		doReturn(getMockPaymentResponse()).when(restTemplate).exchange(any(), eq(PaymentResponse.class));

		Order order = Order.builder().description("testing order").build();
		Payment payment = Payment.builder().amount(20.2).transctionId(31L).build();
		OrderRequest request = OrderRequest.builder().order(order).payment(payment).build();

		OrderResponse response = orderService.createOrder(request);

		assertNotNull(response);
		assertNotNull(response.getOrder());
		assertNotNull(response.getOrder().getId());
		assertNotNull(response.getPayment());
		assertNotNull(response.getPayment().getId());

	}

}
