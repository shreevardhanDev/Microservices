package com.test.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.order.dto.OrderRequest;
import com.test.order.dto.OrderResponse;
import com.test.order.service.OrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

	
	@Autowired
	private OrderService orderSerivce;

	@PostMapping(value = "createOrder")
	public @ResponseBody OrderResponse saveOrder(@RequestBody OrderRequest req) {
		OrderResponse resp = orderSerivce.createOrder(req);
		return resp;
	}

}
