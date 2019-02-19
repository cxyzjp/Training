package com.study.spring.test;

import com.study.spring.beans.Person;
import com.study.spring.primary.ConfigurationAnnotation;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.Map;

public class ConfigurationAnnotationTest {

    @Test
    public void testConfigurationAnnotation(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigurationAnnotation.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(n-> System.out.println("=== " + n));

    }

    @Test
    public void test02(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigurationAnnotation.class);
        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        Arrays.stream(beanNamesForType).forEach(n-> System.out.println("=== " + n));

        Map<String, Person> beansOfType = applicationContext.getBeansOfType(Person.class);
        System.out.println(beansOfType);

    }

}
