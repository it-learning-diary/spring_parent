package com.elvis.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IRuleConfig {
    // 随机调用服务的负载均衡算法
    @Bean
    IRule getIRule(){
        return new RandomRule();
    }
}
