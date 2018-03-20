package com.cxy.shardingjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Map;

@SpringBootApplication
public class ShardingJdbcApplication {

    public static void main(final String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ShardingJdbcApplication.class, args);
        String[] names = applicationContext.getBeanNamesForType(DataSource.class);
        Map<String, DataSource> types = applicationContext.getBeansOfType(DataSource.class);
        System.out.println(Arrays.toString(names));
        System.out.println(types);
    }
}
