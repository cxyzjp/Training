package com.cxy.rabbitproducer.controller;

import com.cxy.rabbitproducer.sender.FanoutSender;
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
@RequestMapping("/fanout")
public class FanoutController {
    private final FanoutSender sender;

    @GetMapping("/test")
    public String test() {
        sender.generateReports("开始生成报表！");
        return "success";
    }
}
