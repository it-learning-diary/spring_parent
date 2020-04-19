package com.elvis.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import ribbonconfig.IRuleImpl;

@SpringBootApplication
@EnableEurekaClient
// 此处的name属性的值就是我们消费者需要访问服务的服务名称(即配置在:application配置文件中的:application.name的值)
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE")
@EnableDiscoveryClient
public class ConsumerOrder8006 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrder8006.class,args);
    }
}
