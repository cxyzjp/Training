package com.example.consumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 * author: bowen
 * date: 2019/7/10
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String hello(){
        return "hi1";
    }

}
