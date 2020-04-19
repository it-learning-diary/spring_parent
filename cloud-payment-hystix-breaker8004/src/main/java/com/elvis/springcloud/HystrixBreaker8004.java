package com.elvis.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
// 激活Hystrix
@EnableHystrix
public class HystrixBreaker8004 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixBreaker8004.class,args);
    }
}
