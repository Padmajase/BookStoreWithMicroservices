package com.example.CartServer.repository;


import com.example.CartServer.model.CartData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartData, Integer> {
}
