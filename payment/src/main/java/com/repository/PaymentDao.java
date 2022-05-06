package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.order.dto.Payment;

public interface PaymentDao extends JpaRepository<Payment, Long> {

}
