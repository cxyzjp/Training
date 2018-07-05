package com.cxy.shardingjdbc.controller;

import com.cxy.shardingjdbc.entity.OrderQuery;
import com.cxy.shardingjdbc.entity.User;
import com.cxy.shardingjdbc.mapper.a.UserAMapper;
import com.cxy.shardingjdbc.mapper.b.UserBMapper;
import com.cxy.shardingjdbc.mapper.sharding.OrderMapper;
import com.cxy.shardingjdbc.mapper.sharding.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sharding")
public class ShardingControllerTest {
    @Autowired
    private OrderMapper orderRepository;

    @RequestMapping("/add")
    public OrderQuery insert(){
        OrderQuery query = new OrderQuery();
//        query.setCreateTime(1);
        query.setBuyer(1);
        query.setcStart(1505998800);
        query.setcEnd(1524327556);
//        query.setuStart(new Date(1517875200000L));
//        query.setuEnd(new Date(1524327556000L));
//        System.out.println(orderRepository.queryAll(query));
        return query;
    }
}
