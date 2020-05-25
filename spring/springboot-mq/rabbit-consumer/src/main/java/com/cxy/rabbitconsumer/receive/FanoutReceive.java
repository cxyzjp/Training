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
public class FanoutReceive {
    @RabbitHandler
    @RabbitListener(queues = "api.report.payment")
    public void payment(String msg) {
        System.out.println("=== api.report.payment receive message: " + msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "api.report.refund")
    public void refund(String msg) {
        System.out.println("=== api.report.refund receive message: " + msg);
    }
}
