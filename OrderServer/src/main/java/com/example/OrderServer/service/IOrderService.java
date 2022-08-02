package com.example.OrderServer.service;

import com.example.OrderServer.dto.OrderDTO;
import com.example.OrderServer.dto.ResponseDTO;
import com.example.OrderServer.model.OrderData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOrderService {
    OrderData createOrderData(String token, OrderDTO orderDTO, int bookId);

    List<OrderData> getOrderList();

    ResponseEntity<ResponseDTO> updateOrder(int orderId, OrderDTO orderDTO);

    void cancelOrderData(int orderId);
}
