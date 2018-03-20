package com.cxy.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.cxy.shardingjdbc.mapper")
public class ShardingJdbcApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ShardingJdbcApplication.class, args);
    }
}
