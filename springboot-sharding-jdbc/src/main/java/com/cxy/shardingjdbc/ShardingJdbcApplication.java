package com.cxy.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.util.Map;

@SpringBootApplication
@MapperScan("com.kb.mapper")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class ShardingJdbcApplication {

    public static void main(final String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ShardingJdbcApplication.class, args);
        String[] names = applicationContext.getBeanNamesForType(DataSource.class);
        Map<String, DataSource> types = applicationContext.getBeansOfType(DataSource.class);
        System.out.println(names.toString());
        System.out.println(types);
    }
}
