package com.cxy.rabbitconsumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description:
 * author: bowen
 * date: 2019/2/19
 */
@Configuration
public class DirectConfig {
    @Bean
    public Queue paymentNotifyQueue() {
        return new Queue("notify.payment");
    }

}
