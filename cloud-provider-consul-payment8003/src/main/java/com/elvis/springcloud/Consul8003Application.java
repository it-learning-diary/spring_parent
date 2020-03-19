package com.elvis.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Consul8003Application {
    public static void main(String[] args) {
        SpringApplication.run(Consul8003Application.class,args);
    }
}
