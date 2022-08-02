package com.example.CustomerServer.rabbitmq;

import com.example.CustomerServer.model.UserData;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

    @RabbitListener(queues = MessageConfig.QUEUE)
    public  void consumerMessageFormatQueue(UserData userData){
        System.out.println("Message Received From Queue : " +userData);
    }
}
