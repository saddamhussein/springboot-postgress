/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.postgresdemo.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author saddam.husein
 */
@Configuration
public class RabbitConfig {
    public static final String QUEUE_ORDERS = "orders-queue";
    public static final String EXCHANGE_ORDERS = "orders-exchange";
//    public static final String QUEUE_DEAD_ORDERS = "dead-orders-queue";

    @Autowired
    org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory;
    
    
    @Bean
    Queue ordersQueue() {
        return QueueBuilder.durable(QUEUE_ORDERS).build();
    }
 
//    @Bean
//    Queue deadLetterQueue() {
//        return QueueBuilder.durable(QUEUE_DEAD_ORDERS).build();
//    }
 
    @Bean
    Exchange ordersExchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_ORDERS).build();
    }
 
    @Bean
    Binding binding(Queue ordersQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(ordersQueue).to(directExchange).with(QUEUE_ORDERS);
    }
    
    

}
