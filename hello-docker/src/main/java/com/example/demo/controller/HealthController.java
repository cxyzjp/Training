package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 * author: bowen
 * date: 2019/5/30
 */
@RestController
public class HealthController {
    @Value("${project-version}")
    private String projectVersion;

    @GetMapping("/hello")
    public String hello() {
        return "hello docker " + projectVersion;
    }

}
