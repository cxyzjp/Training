package com.cxy.rabbitconsumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置一个credit.bank的消息队列并绑定在creditBankExchange交换机上
 * <p>
 * 配置一个credit.finance的消息队列并绑定在creditFinanceExchange交换机上
 */
@Configuration
public class HeadersConfig {
    @Bean
    public Queue creditBankQueue() {
        return new Queue("credit.bank");
    }

    @Bean
    public Queue creditFinanceQueue() {
        return new Queue("credit.finance");
    }

    @Bean
    public HeadersExchange creditBankExchange() {
        return new HeadersExchange("creditBankExchange");
    }

    @Bean
    public HeadersExchange creditFinanceExchange() {
        return new HeadersExchange("creditFinanceExchange");
    }

    /**
     * creditBankExchange交换机的匹配规则是完全匹配，即header attribute参数必须完成一致
     */
    @Bean
    public Binding bindingCreditAExchange(Queue creditBankQueue, HeadersExchange creditBankExchange) {
        Map<String, Object> headerValues = new HashMap<>();
        headerValues.put("type", "cash");
        headerValues.put("aging", "fast");
        return BindingBuilder.bind(creditBankQueue).to(creditBankExchange).whereAll(headerValues).match();
    }

    @Bean
    public Binding bindingCreditBExchange(Queue creditFinanceQueue, HeadersExchange creditFinanceExchange) {
        Map<String, Object> headerValues = new HashMap<>();
        headerValues.put("type", "cash");
        headerValues.put("aging", "fast");
        return BindingBuilder.bind(creditFinanceQueue).to(creditFinanceExchange).whereAny(headerValues).match();
    }
}
