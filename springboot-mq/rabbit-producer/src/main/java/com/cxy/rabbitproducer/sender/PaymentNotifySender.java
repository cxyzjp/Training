package com.cxy.rabbitproducer.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

/**
 * description:
 * author: bowen
 * date: 2019/2/19
 */
@RequiredArgsConstructor
@Component
public class PaymentNotifySender {
    private final AmqpTemplate rabbitTemplate;

    public void sender(String msg) {
        System.out.println("=== notify.payment send message: " + msg);
        rabbitTemplate.convertAndSend("notify.payment", msg);
    }
}
