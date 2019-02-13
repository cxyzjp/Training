package com.study.spring.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class BConditional implements Condition {

    /*
        ConditionContext：判断能使用的上下文
        AnnotatedTypeMetadata： 注解信息
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // ioc的BeanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 当前环境信息
        Environment environment = context.getEnvironment();
        // bean定义的注册类。获取bean的定义信息、移出bean的定义等等
        BeanDefinitionRegistry registry = context.getRegistry();

        // 容器中包含person时满足条件
        return registry.containsBeanDefinition("person");
    }

}
