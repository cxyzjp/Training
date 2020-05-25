package com.study.spring.config;

import com.study.spring.beans.Yellow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /*
        AnnotationMetadata:当前类的注解信息
        BeanDefinitionRegistry：BeanDefinition注册类，可以手动注册bean(BeanDefinitionRegistry.registerBeanDefinition)
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean person = registry.containsBeanDefinition("person");
        if(person){
            registry.registerBeanDefinition("yellow", new RootBeanDefinition(Yellow.class));
        }
    }
}
