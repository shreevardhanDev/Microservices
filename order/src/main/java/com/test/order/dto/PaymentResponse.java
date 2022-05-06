package com.test.order.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentResponse implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private Payment payment;

}
