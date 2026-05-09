package com.test.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.order.entities.Order;


public interface OrderDao extends JpaRepository<Order, Long> {

}
