package com.study.spring.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Cat implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("=== cat destroy... ");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("=== cat init... ");
    }
}
