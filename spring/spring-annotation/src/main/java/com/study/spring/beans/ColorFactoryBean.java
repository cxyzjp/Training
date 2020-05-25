package com.study.spring.beans;

import org.springframework.beans.factory.FactoryBean;

public class ColorFactoryBean implements FactoryBean<Camel> {

    @Override
    public Camel getObject() throws Exception {
        System.out.println("create Camel");
        return new Camel();
    }

    @Override
    public Class<?> getObjectType() {
        return Camel.class;
    }

    /*
        true： 单实例，只会创建一次。容器中只存在一份
        false：多实例，每次获取Bean，调用getObject()。
     */
    @Override
    public boolean isSingleton() {
        return false;
    }
}
