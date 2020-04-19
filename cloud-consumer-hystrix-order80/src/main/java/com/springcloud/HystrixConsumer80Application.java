package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// 激活Hystrix
@EnableHystrix
// 激活Eureka客户端
@EnableEurekaClient
// 激活OpenFeign
@EnableFeignClients
public class HystrixConsumer80Application {
    public static void main(String[] args) {
        SpringApplication.run(HystrixConsumer80Application.class,args);
    }
}
