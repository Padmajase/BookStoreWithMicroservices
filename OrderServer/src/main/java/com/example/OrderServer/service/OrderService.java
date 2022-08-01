package com.example.OrderServer.service;

import com.example.OrderServer.dto.OrderDTO;
import com.example.OrderServer.exception.UserRegistrationException;
import com.example.OrderServer.model.BookData;
import com.example.OrderServer.model.OrderData;
import com.example.OrderServer.model.UserData;
import com.example.OrderServer.repository.OrderRepository;
import com.example.OrderServer.token.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    public OrderData createOrderData(String token, OrderDTO orderDTO, int bookId) {
        int userId = tokenUtil.decodeToken(token);
        UserData userData  = restTemplate.getForObject("http://localhost:8002/user/getById?token=" + token, UserData.class);
//        UserData userData = restTemplate.getForObject("localhost:8002/user/getById/" + userId,UserData.class);
        BookData bookData = restTemplate.getForObject("localhost:8001/book/getById" + bookId,BookData.class);
        if(bookData == null || userData == null){
            throw new UserRegistrationException("User Id or Book Id is Invalid");
        } else {
            OrderData orderData = new OrderData(userData, bookData, orderDTO);
            orderData.setUserData(userData);
            orderData.setBookData(bookData);
            orderData.createOrder(orderDTO);
            orderRepository.save(orderData);
            return orderData;
        }
    }

    @Override
    public OrderData cancelOrder(String token, int orderId) {
        return null;
    }

    @Override
    public List<OrderData> getAllOrdersOfUser(String token) {
        return null;
    }

    @Override
    public List<OrderData> findAllOrders() {
        return null;
    }
}
