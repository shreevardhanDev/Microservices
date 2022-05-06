package com.test.payment.dto;

import java.io.Serializable;

import com.test.order.dto.Payment;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PaymentResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Payment payment;

}
