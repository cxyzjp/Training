package com.study.spring.primary;

import com.study.spring.beans.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/*
    1. @Value
        1. 基本数值。
        2. SpEL #{}。#{20-1}
        3. ${}。配置文件中的值（在运行环境变量中的值）。
            1. @PropertySource 读取外部配置文件中的K/V值保存到运行环境中.

 */
@PropertySource("classpath:/person.properties")
@Configuration
public class ConfigurationProperties {

    @Bean
    public Person person() {
        return new Person();
    }

}
