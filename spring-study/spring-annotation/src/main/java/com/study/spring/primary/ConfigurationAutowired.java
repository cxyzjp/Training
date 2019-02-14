package com.study.spring.primary;

import com.study.spring.beans.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    自动装配。Spring利用依赖注入。
    原理：AutowiredAnnotationBeanPostProcessor

    1. @Autowired 自动注入
        1. 注入方式
            1. 默认优先按类型从容器中寻找。
            2. 如果容器中有多个相同类型的Bean，按照属性名字。(不要随意乱写需要注入的Bean的属性名)
            3. @Qualifier 指定注入的Bean的名称。
            4. @Autowired(required = false) 不存在时不注入
            5. @Primary 没有指定@Qualifier时，默认使用@Primary的Bean
        2. 使用方式
            1. 方法上，方法的参数使用IOC容器中的Bean
            2. 构造器上，构造器的参数使用IOC容器中的Bean。如果只有一个有参构造器，@Autowired可以省略。
            3. 方法或者构造器的参数位置。
            4. @Bean标注的方法，方法参数从容器中获取。
    2. Java规范主键@Resource和@Inject 注入组件。
    3. 想要使用Spring底层组件（ApplicationContext、BeanFactory、xxx）。
        自定义组件实现xxxAware接口，可以注入相关组件.
        xxxAware功能使用xxxAwareProcessor实现的
 */
@Configuration
public class ConfigurationAutowired {

    @Bean
    public Person person() {
        return new Person();
    }

}
