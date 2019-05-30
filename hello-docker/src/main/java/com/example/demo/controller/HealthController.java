package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 * author: bowen
 * date: 2019/5/30
 */
@RestController
public class HealthController {

    @GetMapping("/hello")
    public String hello() {
        return "hello docker";
    }

}
