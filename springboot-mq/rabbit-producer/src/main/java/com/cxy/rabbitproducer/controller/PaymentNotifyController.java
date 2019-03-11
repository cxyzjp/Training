package com.cxy.rabbitproducer.controller;

import com.cxy.rabbitproducer.sender.PaymentNotifySender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 * author: bowen
 * date: 2019/2/19
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("payment")
public class PaymentNotifyController {
    private final PaymentNotifySender paymentNotifySender;

    @GetMapping("/test")
    public String test(@RequestParam String msg){
        paymentNotifySender.sender(msg + " -> " + System.currentTimeMillis());
        return "success";
    }
}
