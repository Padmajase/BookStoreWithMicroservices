package com.example.OrderServer.controller;

import com.example.OrderServer.dto.OrderDTO;
import com.example.OrderServer.dto.ResponseDTO;
import com.example.OrderServer.model.OrderData;
import com.example.OrderServer.service.EmailService;
import com.example.OrderServer.service.IOrderService;
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

    /*************** placing order for book ***************/
    @PostMapping("/placeorder")
    public ResponseEntity<ResponseDTO> placeOrder(@RequestHeader(name = "token") String token,
                                                  @RequestBody OrderDTO orderDTO,
                                                  @RequestParam int bookId) {
        OrderData orderData = orderService.createOrderData(token, orderDTO, bookId);
        ResponseDTO responseDTO = new ResponseDTO("Order placed", orderData);
        emailService.sendEmail(orderData.getUserData().getEmailId(), "Order Placed Successfully.",
                "Dear " + orderData.getUserData().getFirstName()
                        + ", Your order has placed for Book : "
                        + orderData.getBookData().getBookName());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

   /*************** getting order list  ***************/
    @GetMapping(value = {"","/","/list"})
    public ResponseEntity<ResponseDTO> getOrderList() {
        List<OrderData> listOfOrder;
        listOfOrder = orderService.getOrderList();
        ResponseDTO responseDTO = new ResponseDTO("user order list : ", listOfOrder);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /*************** update order by ID ***************/
    @PutMapping("/update/{orderId}")
    public ResponseEntity<ResponseDTO> updateBookData(@PathVariable("orderId") int orderId,
                                                      @RequestBody OrderDTO orderDTO){
        return  orderService.updateUserById(orderId, orderDTO);
    }

    /*************** delete order by its Id ***************/
    @DeleteMapping("/cancel/{orderId}")
    public ResponseEntity<ResponseDTO> deleteOrderById(@PathVariable int orderId){
        orderService.cancelOrderData(orderId);
        ResponseDTO respDTO = new ResponseDTO("Order Cancelled Successfully", "with order Id : " +orderId);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
}
