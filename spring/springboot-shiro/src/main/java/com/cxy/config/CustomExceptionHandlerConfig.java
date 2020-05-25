package com.cxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ms.exception.base.CustomExceptionHandler;

@Configuration
public class CustomExceptionHandlerConfig {

	@Bean
	public CustomExceptionHandler customExceptionHandler() {
		return new CustomExceptionHandler();
	}

}
