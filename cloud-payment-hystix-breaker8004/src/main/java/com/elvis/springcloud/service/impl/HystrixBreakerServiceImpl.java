package com.elvis.springcloud.service.impl;

import com.elvis.springcloud.service.HystrixBreakerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HystrixBreakerServiceImpl implements HystrixBreakerService {

    @Override
    @HystrixCommand(fallbackMethod="fallbackBreakerTest",commandProperties={
            @HystrixProperty(name="circuitBreaker.enabled",value="true"), // 开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="10"), // 窗口期内的请求总次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="1000"), // 时间休眠窗
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="60") // 失败率到达这个指标都开启断路器
            // 解释:在1000毫秒内,如果请求总数超过10次,且失败率达到百分之60以上,则开启断路器
    })
    public String breakerTest(Integer number) {
        // 小于0是抛出异常,模拟服务请求出现异常,然后进行熔断
        if(number < 0){
            throw new RuntimeException();
        }
        String msg = Thread.currentThread().getName()+":请求服务反馈正常,请求参数为:"+number;
        return msg;
    }

    public String fallbackBreakerTest(Integer number) {
        String msg = Thread.currentThread().getName()+":请求服务反馈异常,服务熔断,请求参数为:"+number;
        return msg;
    }
}
