package com.cxy.rabbitproducer.controller;

import com.cxy.rabbitproducer.sender.TopicSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 * author: bowen
 * date: 2019/2/19
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/topic")
public class TopicController {
    private final TopicSender sender;

    @GetMapping("/test")
    public String test(){
        sender.user("用户管理！");
        sender.userQuery("查询用户信息！");

        sender.order("订单管理！");
        sender.orderQuery("查询订单信息！");
        sender.orderDetailQuery("查询订单详情信息！");
        return "success";
    }
}
