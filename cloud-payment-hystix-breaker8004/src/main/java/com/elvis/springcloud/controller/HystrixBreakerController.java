package com.elvis.springcloud.controller;

import com.elvis.springcloud.service.HystrixBreakerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HystrixBreakerController {
    @Autowired
    private HystrixBreakerService hystrixBreakerService;

    @RequestMapping("/payment/hystrix/breaker/{number}")
    public String breakerTest(@PathVariable("number") Integer number){
        String s = hystrixBreakerService.breakerTest(number);
        log.info(s);
        return s;
    }
}
