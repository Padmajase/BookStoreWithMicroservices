package com.example.OrderServer.service;

import com.example.OrderServer.dto.OrderDTO;
import com.example.OrderServer.dto.ResponseDTO;
import com.example.OrderServer.exception.UserRegistrationException;
import com.example.OrderServer.model.BookData;
import com.example.OrderServer.model.OrderData;
import com.example.OrderServer.model.UserData;
import com.example.OrderServer.repository.OrderRepository;
import com.example.OrderServer.token.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    /*************** injecting TokenUtil Object ***************/
    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private RestTemplate restTemplate;


    /*************** injecting Order Repository Object ***************/
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public OrderData createOrderData(OrderDTO orderDTO, int bookId, int userId) {
//        int userId = tokenUtil.decodeToken(token);
//        UserData userData  = restTemplate.getForObject("http://localhost:8002/user/getById?token=" + token, UserData.class);
        UserData userData = restTemplate.getForObject("localhost:8002/user/get/" + userId,UserData.class);
        BookData bookData = restTemplate.getForObject("localhost:8001/book/getById/" + bookId,BookData.class);
        if(bookData == null || userData == null){
            throw new UserRegistrationException("User Id or Book Id is Invalid");
        } else {
            OrderData orderData = new OrderData(userData, bookData, orderDTO);
            orderRepository.save(orderData);
            return orderData;
        }
    }

    @Override
    public List<OrderData> getOrderList() {
        return orderRepository.findAll();
    }

    @Override
    public ResponseEntity<ResponseDTO> updateOrder(int orderId, String address) {
        Optional<OrderData> order = orderRepository.findById(orderId);
        if(order.isEmpty())
            throw new UserRegistrationException("no order present");
//        not recognized optional
        order.get().setAddress(address);
        orderRepository.save(order.get());
        ResponseDTO responseDTO = new ResponseDTO("order updated", order);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @Override
    public void cancelOrderData(int orderId) {
        orderRepository.deleteById(orderId);
    }
}
