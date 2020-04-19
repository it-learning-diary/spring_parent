package com.elvis.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean
    //@LoadBalanced // 使用默认的轮询负载均衡,这里注释掉,使用我们自己书写的算法实现负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
