package com.example.OrderServer.service;

import com.example.OrderServer.dto.OrderDTO;
import com.example.OrderServer.dto.ResponseDTO;
import com.example.OrderServer.dto.User;
import com.example.OrderServer.exception.OrderException;
import com.example.OrderServer.dto.Book;
import com.example.OrderServer.model.OrderData;
import com.example.OrderServer.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class OrderService implements IOrderService {

    @Autowired
    RestTemplate template;
    /*************** injecting Order Repository Object ***************/
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderData createOrderData(OrderDTO orderDTO) {
        log.info("before url execution");
        User user;
        Book book;
//        user = template.getForObject("http://localhost:8002/user/get/{userId}" + orderDTO.getUserId(), User.class);
        user = template.getForObject("http://localhost:8002/user/get/" + orderDTO.getUserId(), User.class);

        log.info("after user rest");
        book = template.getForObject("http://localhost:8001/book/get/" +orderDTO.getBookId(), Book.class);
        Integer b = book.getQuantity();
        log.info("book object", b);
        if(book == null){
            throw new OrderException("order Id or Book Id is Invalid");
        } else {
            if(orderDTO.getQuantity() > book.getQuantity()){
                throw new OrderException("quantity is greater");
            } else {
                Integer totalPrice = orderDTO.getQuantity() * book.getBookPrice();
                OrderData orderData = new OrderData(orderDTO);
                orderData.setPrice(totalPrice);
                log.info("before save");
                orderRepository.save(orderData);
                return orderData;
            }
        }
    }

    @Override
    public List<OrderData> getOrderList() {
        if(orderRepository.findAll().isEmpty())
            throw new OrderException("No any orders");
        else
            return orderRepository.findAll();
    }

    /*************** getting order by its id ***************/
    @Override
    public OrderData getOrderById(Integer orderId) {
        return orderRepository.findById(orderId).orElseThrow(()
                -> new OrderException("order with id " + orderId + " does not exist in database..!"));
    }


    @Override
    public ResponseEntity<ResponseDTO> updateOrder(Integer orderId, OrderDTO orderDTO) {
        OrderData order = this.getOrderById(orderId);
        if(order == null) {
            ResponseDTO respDTO = new ResponseDTO("order not present with given Id ", orderId);
            return new ResponseEntity<>(respDTO, HttpStatus.OK);
        } else {
            order.updateOrder(orderDTO);
            orderRepository.save(order);
            ResponseDTO respDTO = new ResponseDTO("order Updated Successfully ", order);
            return new ResponseEntity<>(respDTO, HttpStatus.OK);
        }
    }

    @Override
    public void cancelOrderData(Integer orderId) {
        orderRepository.deleteById(orderId);
    }
}
