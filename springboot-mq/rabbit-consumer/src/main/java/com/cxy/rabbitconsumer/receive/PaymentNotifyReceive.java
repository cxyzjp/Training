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
@RabbitListener(queues = "notify.payment")
public class PaymentNotifyReceive {

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("=== notify.payment receive message: "+msg);
    }

}
