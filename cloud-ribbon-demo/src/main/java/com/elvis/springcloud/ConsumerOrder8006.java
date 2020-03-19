package com.elvis.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import ribbonconfig.IRuleImpl;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = IRuleImpl.class)
@EnableDiscoveryClient
public class ConsumerOrder8006 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrder8006.class,args);
    }
}
