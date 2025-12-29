package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.order.dto.Order;


public interface OrderDao extends JpaRepository<Order, Long> {

}
