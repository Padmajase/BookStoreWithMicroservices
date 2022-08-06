package com.example.OrderServer.service;

import com.example.OrderServer.dto.OrderDTO;
import com.example.OrderServer.dto.ResponseDTO;
import com.example.OrderServer.model.OrderData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOrderService {
    OrderData createOrderData(OrderDTO orderDTO, int bookId, int userId);

    List<OrderData> getOrderList();

    ResponseEntity<ResponseDTO> updateOrder(int orderId, String address);

    void cancelOrderData(int orderId);
}
