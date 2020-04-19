package com.elvis.springcloud;

import com.elvis.springcloud.config.IRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@RibbonClient(value = "CLOUD-PAYMENT-SERVICE",configuration = IRuleConfig.class)
public class ConsumerFeignOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignOrder80.class,args);
    }
}
