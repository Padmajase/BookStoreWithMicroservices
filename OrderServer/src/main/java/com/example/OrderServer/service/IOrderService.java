package com.example.OrderServer.service;

import com.example.OrderServer.dto.OrderDTO;
import com.example.OrderServer.dto.ResponseDTO;
import com.example.OrderServer.model.OrderData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOrderService {
    OrderData createOrderData(OrderDTO orderDTO);

    List<OrderData> getOrderList();

    OrderData getOrderById(Integer orderId);

    ResponseEntity<ResponseDTO> updateOrder(Integer orderId, OrderDTO orderDTO);

    void cancelOrderData(Integer orderId);
}
