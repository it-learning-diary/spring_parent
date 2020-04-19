package com.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 针对ConsumerService中绑定的服务的方法的服务降级类
 */
// 注入spring容器
@Component
public class ConsumerHystrixService implements  ConsumerService {
    @Override
    public String ok() {
        return "ok请求方法失败,进入服务熔断方法!";
    }

    @Override
    public String timeOut(Integer sleepTime) {
        return "timeOut请求方法失败,进入服务熔断方法!";
    }

    @Override
    public String breakOut() {
        return null;
    }
}
