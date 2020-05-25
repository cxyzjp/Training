package com.study.spring.primary;

import com.study.spring.beans.ColorFactoryBean;
import com.study.spring.beans.Person;
import com.study.spring.beans.Red;
import com.study.spring.condition.BConditional;
import com.study.spring.config.MyImportBeanDefinitionRegistrar;
import com.study.spring.config.MyImportSelector;
import com.study.spring.config.MyTypeFilter;
import org.springframework.context.annotation.*;

/*
    给容器中注册Bean的方法
    1.包扫描+组件注解（@Controller、@Service、@Component等）
    2.@Bean
    3.@Import
    4.使用spring提供的 FactoryBean
 */

/*
    1. @Configuration: 配置类
    2. @ComponentScan: 需要扫描的包
        1. 示例
        @ComponentScan(value = "com.study.spring", excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)
        })
        @ComponentScan(value = "com.study.spring", useDefaultFilters = false, includeFilters = {
            @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
        })
        2. excludeFilters: 需要排除组件的规则
        3. includeFilters: 需要指定扫描组件的规则。
            需要添加useDefaultFilters = false才能生效，否者按默认规则扫描。
            经测试，普通类只要满足条件，就会被注册到容器中。
        4. @ComponentScan.Filter：
            1. type：
                FilterType.ANNOTATION：按照注解
                FilterType.ASSIGNABLE_TYPE：按照指定类型
                FilterType.ASPECTJ：AspectJ表达式
                FilterType.REGEX：正则表达式。使用参数pattern配置表达式
                FilterType.CUSTOM：使用自定义规则
    3. @Import 快速导入bean到容器中。组件名默认为全类名。
        1. 示例：
            @Import({Red.class, MyImportSelector.class})
        2. ImportSelector：返回需要导入的bean全类名数组。
        3. ImportBeanDefinitionRegistrar：
 */
@Configuration
@ComponentScan(value = "com.study.spring", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
})
@Import({Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class ConfigurationAnnotation {
    /*
        1. @Bean bean的名称优先选用@Bean注解中的value，如果没有配置选用方法名
        2. @Scope 调整作用域。默认是单实例。
            scopeName:
                SCOPE_PROTOTYPE: 多实例。IOC容器启动不会创建对象，每次获取时创建对象。
                SCOPE_SINGLETON： 单实例。IOC容器启动时就会创建对象（new AnnotationConfigApplicationContext(ConfigurationAnnotation.class)）
                SCOPE_REQUEST： 同一次请求一个实例
	            SCOPE_SESSION： 同一次会话一个实例
	    3. @Lazy 懒加载。单实例bean在容器启动时不创建，在第一次获取对象时创建对象。
     */
    @Lazy
    @Scope
    @Bean("person")
    public Person person() {
        return new Person("AAA", 21);
    }

    /*
        @Conditional 按照一定条件给容器中注册bean。放到类上，类中组件统一设置，满足条件才能注册。
     */
    @Conditional({BConditional.class})
    @Bean
    public Person BBB() {
        return new Person("BBB", 22);
    }

    /*
        applicationContext.getBean("colorFactoryBean");获取到的是工厂Bean调用getObject创建的对象。
        applicationContext.getBean("&colorFactoryBean");获取到的是工厂Bean本身，BeanFactory中一个前缀配置。
    */
    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }
}
