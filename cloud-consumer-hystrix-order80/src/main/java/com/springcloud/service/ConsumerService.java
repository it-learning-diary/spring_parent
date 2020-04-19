package com.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// 绑定服务名称为CLOUD-PROVIDER-HYSTRIX-PAYMENT8003的服务
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT8003",fallback = ConsumerHystrixService.class)
public interface ConsumerService {
    @RequestMapping("/payment/ok")
    public String ok();
    @RequestMapping("/payment/timeout/{sleepTime}")
    public String timeOut(@PathVariable("sleepTime") Integer sleepTime);
    @RequestMapping("/payment/breakout")
    public String breakOut();
}
