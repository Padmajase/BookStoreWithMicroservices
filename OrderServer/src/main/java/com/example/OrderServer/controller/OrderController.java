package com.example.OrderServer.controller;

import com.example.OrderServer.dto.OrderDTO;
import com.example.OrderServer.dto.ResponseDTO;
import com.example.OrderServer.model.OrderData;
import com.example.OrderServer.rabbitmq.MessageConfig;
import com.example.OrderServer.service.EmailService;
import com.example.OrderServer.service.IOrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/*************** placing order here ***************/
@RestController
@RequestMapping("/order")
public class OrderController {

    /*************** injecting order interface object here ***************/
    @Autowired
    IOrderService orderService;

    /*************** injecting order interface object here ***************/
    @Autowired
    EmailService emailService;

    /*************** injecting Rabbit Template Object ***************/
    @Autowired
    RabbitTemplate rabbitTemplate;


    /*************** placing order for book ***************/
    @PostMapping("/placeorder")
    public ResponseEntity<ResponseDTO> placeOrder(@RequestBody OrderDTO orderDTO) {
        OrderData orderData = orderService.createOrderData(orderDTO);
        ResponseDTO responseDTO = new ResponseDTO("Order placed", orderData);
        emailService.sendEmail("padmajapawar7@gmail.com", "Order Placed Successfully.","");
//        rabbitTemplate.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY, responseDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

   /*************** getting order list  ***************/
    @GetMapping(value = {"","/","/get"})
    public ResponseEntity<ResponseDTO> getOrderList() {
        List<OrderData> listOfOrder;
        listOfOrder = orderService.getOrderList();
        ResponseDTO responseDTO = new ResponseDTO("user order list : ", listOfOrder);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /*************** update order by ID ***************/
    @PutMapping("/update/{orderId}")
    public ResponseEntity<ResponseDTO> updateOrder(@PathVariable("orderId") Integer orderId,
                                                      @RequestBody OrderDTO orderDTO){
        return  orderService.updateOrder(orderId, orderDTO);
    }

    /*************** delete order by its Id ***************/
    @DeleteMapping("/cancel/{orderId}")
    public ResponseEntity<ResponseDTO> deleteOrderById(@PathVariable Integer orderId){
        orderService.cancelOrderData(orderId);
        ResponseDTO respDTO = new ResponseDTO("Order Cancelled Successfully", "with order Id : " +orderId);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
}
