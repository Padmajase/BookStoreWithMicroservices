package com.example.OrderServer.repository;

import com.example.OrderServer.model.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderData, Integer> {
}
