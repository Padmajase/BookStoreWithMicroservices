package com.example.BookServer.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessageConfig {
    public static final String QUEUE = "book_Queue";
    public static final String EXCHANGE = "exchange";
    public static final String ROUTING_KEY = "rabbit_Routing_Key";

    /***************  creating queue  ***************/
    @Bean
    public Queue queue(){
        return new Queue(QUEUE);
    }

    /***************  creating exchange ***************/
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

    /***************  binding queue to exchange ***************/
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    /*************** conveting object into string ***************/
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
