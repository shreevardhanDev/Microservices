package com.test.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.payment.entities.Payment;

public interface PaymentDao extends JpaRepository<Payment, Long> {

}
