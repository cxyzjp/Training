package com.cxy.shardingjdbc;

import com.cxy.shardingjdbc.service.DemoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ShardingJdbcApplication {

	public static void main(final String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(ShardingJdbcApplication.class, args);
//		applicationContext.getBean(DemoService.class).demo();
	}
}
