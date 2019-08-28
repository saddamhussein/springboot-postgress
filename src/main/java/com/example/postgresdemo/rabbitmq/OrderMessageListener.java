package com.example.postgresdemo.rabbitmq;

import com.example.postgresdemo.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author saddam.husein
 */
@Component
public class OrderMessageListener {
    
    static final Logger logger = LoggerFactory.getLogger(OrderMessageListener.class);
 
    @RabbitListener(queues = RabbitConfig.QUEUE_ORDERS)
    public void processOrder(Order order) {
        // code for notification here
        logger.info("Order Received: "+order);
    }
}
