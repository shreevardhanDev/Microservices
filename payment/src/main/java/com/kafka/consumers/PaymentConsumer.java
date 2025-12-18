//package com.kafka.consumers;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//
//import com.test.order.dto.Payment;
//import com.test.payment.service.PaymentService;
//
//public class PaymentConsumer {
//
//	@Autowired
//	private PaymentService paymentService;
//
//	private static final Logger logger = LoggerFactory.getLogger(PaymentConsumer.class);
//
//	@KafkaListener(id = "consume", topics = "payment",containerFactory = "paymentFactory1")
//	public void consume(Payment payment) {
//		payment.setDescription("consume1");
//		paymentService.kafkaPaymentProcessing(payment);
//		logger.info("payment consume1");
//	}
//	
//	@KafkaListener(id = "consumePayent2", topics = "payment",containerFactory = "paymentFactory2")
//	public void consumePayent2(Payment payment) {
//		payment.setDescription("consume2");
//		paymentService.kafkaPaymentProcessing(payment);
//		logger.info("payment consume2");
//	}
//	
//
//}
