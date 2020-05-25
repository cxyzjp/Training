package com.cxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ms.common.redis.RedisCacheUtil;

@Configuration
public class RedisCacheUtilBeanConfig<T> {
	
	@Bean
	public RedisCacheUtil<T> redisCacheUtil() {
		return new RedisCacheUtil<T>();
	}

}

