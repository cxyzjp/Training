package com.example.demo.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * description
 *
 * @author Bowen
 * @date 2018/12/12
 */
@RestController
public class SampleController {

    private static Random random = new Random();

    private final Counter requestTotal;

    @Autowired
    public SampleController(MeterRegistry meterRegistry) {
        requestTotal = Counter.builder("my_job")
                .tags("name", "job1")
                .description("Job 1 execute count").register(meterRegistry);
    }

    @GetMapping("/endpoint")
    public void endpoint() {
        requestTotal.increment();
    }
}
