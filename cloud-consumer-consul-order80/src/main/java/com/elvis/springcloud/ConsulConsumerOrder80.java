package com.elvis.springcloud;

import com.elvis.ribbonconfig.IRuleImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = IRuleImpl.class)
public class ConsulConsumerOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsulConsumerOrder80.class,args);
    }
}
