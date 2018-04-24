package com.cxy.shardingjdbc.controller;

import com.cxy.shardingjdbc.entity.User;
import com.cxy.shardingjdbc.mapper.a.UserAMapper;
import com.cxy.shardingjdbc.mapper.b.UserBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ab")
public class ABControllerTest {
    @Autowired
    private UserAMapper userAMapper;
    @Autowired
    private UserBMapper userBMapper;

    @GetMapping("/add")
    public String insert(){
        User user = new User();
        user.setCreateTime(1);
        userAMapper.insert(user);

        user = new User();
        user.setCreateTime(2);
        userBMapper.insert(user);
        return "";
    }

    @GetMapping("/list")
    public String list(){
        System.out.println(userAMapper.selectAll());
        System.out.println(userBMapper.selectAll());
        return "";
    }
}
