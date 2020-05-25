package com.study.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/health")
public class HealthController {

    @GetMapping
    public String hello() {
        return "hello world";
    }

}
