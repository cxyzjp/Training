package com.cxy.rabbitconsumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置一个api.core的消息队列并绑定在coreExchange交换机上（交换机的匹配规则为api.core.*）
 * <p>
 * 配置一个api.payment的消息队列并绑定在paymentExchange交换机上（交换机的匹配规则为api.payment.#）
 * <p>
 * TopicExchange交换机支持使用通配符*、#
 * *号只能向后多匹配一层路径。
 * #号可以向后匹配多层路径。
 */
@Configuration
public class TopicConfig {
    @Bean
    public Queue coreQueue() {
        return new Queue("api.core");
    }

    @Bean
    public Queue paymentQueue() {
        return new Queue("api.payment");
    }

    @Bean
    public TopicExchange coreExchange() {
        return new TopicExchange("coreExchange");
    }

    @Bean
    public TopicExchange paymentExchange() {
        return new TopicExchange("paymentExchange");
    }

    /**
     * 通配符“*”只能向后多匹配一层路径
     */
    @Bean
    public Binding bindingCoreExchange(Queue coreQueue, TopicExchange coreExchange) {
        return BindingBuilder.bind(coreQueue).to(coreExchange).with("api.core.*");
    }

    @Bean
    public Binding bindingPaymentExchange(Queue paymentQueue, TopicExchange paymentExchange) {
        return BindingBuilder.bind(paymentQueue).to(paymentExchange).with("api.payment.#");
    }
}
