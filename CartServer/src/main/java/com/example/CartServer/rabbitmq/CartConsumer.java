package com.example.CartServer.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CartConsumer {

    @RabbitListener(queues = MessageConfig.QUEUE)
    public  void consumerMessageFormatQueue(CartRabbit cartRabbit){
        System.out.println("Message Received From Queue : " +cartRabbit);
    }
}
