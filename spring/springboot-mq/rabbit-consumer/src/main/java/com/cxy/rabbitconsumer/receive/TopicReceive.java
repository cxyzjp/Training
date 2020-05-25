package com.cxy.rabbitconsumer.receive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * description:
 * author: bowen
 * date: 2019/2/19
 */
@Component
public class TopicReceive {

    /**
     * 监听routingKey为api.core的队列消息
     */
    @RabbitHandler
    @RabbitListener(queues = "api.core")
    public void user(String msg) {
        System.out.println("=== api.core receive message: " + msg);
    }

    /**
     * 监听routingKey为api.payment的队列消息
     */
    @RabbitHandler
    @RabbitListener(queues = "api.payment")
    public void order(String msg) {
        System.out.println("=== api.payment.order receive message: " + msg);
    }
}
