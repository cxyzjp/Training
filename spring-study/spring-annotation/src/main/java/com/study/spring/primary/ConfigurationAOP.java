package com.study.spring.primary;

import com.study.spring.aop.LogAspects;
import com.study.spring.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/*
    使用步骤：
    1. 将业务类和切面类加入到容器中，并告诉Spring切面类（@Aspect）
    2. 在切面类每个通知方法上标注注解。切入点表达式
    3. 开启基于注解的AOP。@EnableAspectJAutoProxy

    原理：关键@EnableAspectJAutoProxy
    1. @Import(AspectJAutoProxyRegistrar)
        注册：AnnotationAwareAspectJAutoProxyCreator -> AspectJAwareAdvisorAutoProxyCreator -> AbstractAdvisorAutoProxyCreator
            -> AbstractAdvisorAutoProxyCreator -> AbstractAutoProxyCreator -> SmartInstantiationAwareBeanPostProcessor,BeanFactoryAware
        有BeanFactoryAware接口和后置处理器的功能，寻找有使用过的痕迹，断点调试

    流程：
    1. 传入主配置类，创建IOC容器
    2. 调用refresh方法刷新容器
    3. registerBeanPostProcessors()
        1. 获取IOC容器中已经定义的需要创建的PostProcessors
        2. 给容器中加其他的PostProcessors
        3. 分离并且优先注册实现了PriorityOrdered的PostProcessors 然后Ordered 最后其他没实现优先级接口的bean
        4. 注册BeanPostProcessors，实际上就是BeanPostProcessors对象保存到容器中
        5. 创建internalAutoProxyCreator的
 */
@EnableAspectJAutoProxy
@Configuration
public class ConfigurationAOP {

    // 业务逻辑类
    @Bean
    public MathCalculator mathCalculator() {
        return new MathCalculator();
    }

    /*
        切面类。需要加上@Aspect，告诉容器该类是切面类
     */
    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }
}
