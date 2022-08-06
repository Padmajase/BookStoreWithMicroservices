package com.example.CartServer.rabbitmq;

import com.example.CartServer.model.CartData;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CartConsumer {

    @RabbitListener(queues = MessageConfig.QUEUE)
    public  void consumerMessageFormatQueue(CartData cartData){
        System.out.println("Message Received From Queue : " +cartData);
    }
}
