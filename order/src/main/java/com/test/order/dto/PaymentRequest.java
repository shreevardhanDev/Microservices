package com.test.order.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PaymentRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Payment payment;

}
