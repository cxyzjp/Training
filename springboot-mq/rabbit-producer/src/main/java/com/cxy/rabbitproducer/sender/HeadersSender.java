package com.cxy.rabbitproducer.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * description:
 * author: bowen
 * date: 2019/2/19
 */
@RequiredArgsConstructor
@Component
public class HeadersSender {
    private final AmqpTemplate rabbitTemplate;

    /**
     * 发送消息至【creditBankExchange】交换机且routingKey为【credit.bank】
     */
    public void creditBank(Map<String, Object> head, String msg) {
        System.out.println("=== credit.bank send message: " + msg);
        rabbitTemplate.convertAndSend("creditBankExchange", "credit.bank", getMessage(head, msg));
    }

    /**
     * 发送消息至【creditFinanceExchange】交换机且routingKey为【credit.finance】
     */
    public void creditFinance(Map<String, Object> head, String msg) {
        System.out.println("=== credit.finance send message: " + msg);
        rabbitTemplate.convertAndSend("creditFinanceExchange", "credit.finance", getMessage(head, msg));
    }

    private Message getMessage(Map<String, Object> head, Object msg) {
        MessageProperties messageProperties = new MessageProperties();
        for (Map.Entry<String, Object> entry : head.entrySet()) {
            messageProperties.setHeader(entry.getKey(), entry.getValue());
        }
        MessageConverter messageConverter = new SimpleMessageConverter();
        return messageConverter.toMessage(msg, messageProperties);
    }
}
