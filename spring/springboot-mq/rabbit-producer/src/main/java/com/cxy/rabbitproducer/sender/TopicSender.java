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
public class TopicSender {
    private final AmqpTemplate rabbitTemplate;

    /**
     * 发送消息至【coreExchange】交换机且routingKey为【api.core.user】
     */
    public void user(String msg) {
        System.out.println("=== api.core.user send message: " + msg);
        rabbitTemplate.convertAndSend("coreExchange", "api.core.user", msg);
    }

    /**
     * 发送消息至【coreExchange】交换机且routingKey为【api.core.user.query】
     */
    public void userQuery(String msg) {
        System.out.println("=== api.core.user.query send message: " + msg);
        rabbitTemplate.convertAndSend("coreExchange", "api.core.user.query", msg);
    }

    // ==========================================================================

    /**
     * 发送消息至【paymentExchange】交换机且routingKey为【api.payment.order】
     */
    public void order(String msg) {
        System.out.println("=== api.payment.order send message: " + msg);
        rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order", msg);
    }

    /**
     * 发送消息至【paymentExchange】交换机且routingKey为【api.payment.order.query】
     */
    public void orderQuery(String msg) {
        System.out.println("=== api.payment.order.query send message: " + msg);
        rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order.query", msg);
    }

    /**
     * 发送消息至【paymentExchange】交换机且routingKey为【api.payment.order.detail.query】
     */
    public void orderDetailQuery(String msg) {
        System.out.println("=== api.payment.order.detail.query send message: " + msg);
        rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order.detail.query", msg);
    }
}
