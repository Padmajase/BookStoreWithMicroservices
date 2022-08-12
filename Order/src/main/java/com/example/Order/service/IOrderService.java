package com.example.Order.service;

import com.example.Order.dto.OrderDTO;
import com.example.Order.model.Order;

public interface IOrderService {

    public Order createOrder(OrderDTO orderDTO);
}
