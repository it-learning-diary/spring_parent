package com.elvis.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    // Feign调用的日志输出级别
    @Bean
    Logger.Level  getLogger(){
        return Logger.Level.FULL;
    }
}
