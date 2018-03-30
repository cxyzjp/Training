package com.cxy.thrift.impl;

import com.cxy.server.service.HelloService;
import com.cxy.thrift.Hello;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloThrift implements Hello.Iface {

    @Autowired
    private HelloService helloService;

    @Override
    public String helloString(String para) throws TException {
//        return helloService.sayHello(para);
        return "hello " + para;
    }

}
