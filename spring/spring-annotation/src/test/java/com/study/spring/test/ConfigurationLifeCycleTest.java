package com.study.spring.test;

import com.study.spring.primary.ConfigurationBeanLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class ConfigurationLifeCycleTest {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigurationBeanLifeCycle.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(n -> System.out.println("=== " + n));

        applicationContext.close();
    }

}
