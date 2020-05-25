package com.study.spring.test;

import com.study.spring.aop.MathCalculator;
import com.study.spring.primary.ConfigurationAOP;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class ConfigurationAOPTest {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigurationAOP.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(n -> System.out.println("=== " + n));

        MathCalculator calculator = applicationContext.getBean(MathCalculator.class);
        calculator.div(1, 0);

        applicationContext.close();
    }

}
