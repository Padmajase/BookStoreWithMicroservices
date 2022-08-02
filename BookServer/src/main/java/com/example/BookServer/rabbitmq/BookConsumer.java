package com.example.BookServer.rabbitmq;

import com.example.BookServer.model.BookData;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BookConsumer {

    @RabbitListener(queues = MessageConfig.QUEUE)
    public  void consumerMessageFormatQueue(BookData bookData){
        System.out.println("Message Received From Queue : " +bookData);
    }
}
