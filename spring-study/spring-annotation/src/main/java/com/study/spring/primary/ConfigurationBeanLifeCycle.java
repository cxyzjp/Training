package com.study.spring.primary;

import com.study.spring.beans.Car;
import com.study.spring.beans.Cat;
import com.study.spring.beans.Dog;
import com.study.spring.config.MyBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
    1. Bean的生命周期：创建--初始化--销毁
        创建：单实例在容器启动的时候创建；多实例在每次获取的时候创建
        初始化：对象创建完成，赋值完，调用初始化方法
        销毁：单实例在容器关闭的时候；多实例时容器不会调用销毁方法

    2. 初始化销毁方法：
        1. 通过@Bean指定初始化和销毁方法。
        2. 通过实现接口。InitializingBean和DisposableBean
        3. 使用@PostConstruct,@PreDestroy
        4. BeanPostProcessor 后置处理器
            1. postProcessBeforeInitialization：初始化之前(上面的初始化和销毁方法)
            2. postProcessAfterInitialization：初始化之后(上面的初始化和销毁方法)
            3. Spring底层对BeanPostProcessor的使用。Bean的赋值，生命周期主键，@Autowired等等。
 */
@Configuration
@ComponentScan(value = "com.study.spring.beans")
public class ConfigurationBeanLifeCycle {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Car car() {
        return new Car();
    }

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor(){
        return new MyBeanPostProcessor();
    }

    @Bean
    public Cat cat() {
        return new Cat();
    }

    @Bean
    public Dog dog() {
        return new Dog();
    }
}
