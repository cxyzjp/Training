package com.study.spring.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Dog {

    public Dog(){
        System.out.println("=== dog constructor...");
    }

    // 对象创建并且赋值完成之后调用
    @PostConstruct
    public void postConstruct(){
        System.out.println("=== dog @PostConstruct...");
    }

    // 对象销毁之前调用
    @PreDestroy
    public void preDestroy(){
        System.out.println("=== dog @PreDestroy...");
    }
}
