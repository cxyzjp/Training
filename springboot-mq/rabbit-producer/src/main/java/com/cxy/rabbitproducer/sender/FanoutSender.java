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
public class FanoutSender {
    private final AmqpTemplate rabbitTemplate;

    /**
     * 发送消息至【reportExchange】交换机
     */
    public void generateReports(String msg) {
        System.out.println("=== api.generate.reports send message: " + msg);
        rabbitTemplate.convertAndSend("reportExchange", "api.generate.reports", msg);
    }

}
