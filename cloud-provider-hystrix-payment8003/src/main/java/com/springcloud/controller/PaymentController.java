package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.service.PaymentService;
import com.springcloud.service.impl.PaymentServiceImpl;
import javafx.beans.DefaultProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
// 设置全局默认的服务降级响应
@DefaultProperties(defaultFallback = "globalHystrixFallback")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // 直接反馈信息
    @RequestMapping("/payment/ok")
    // 不指定fallback属性时,默认使用DefaultProperties中指定的降级响应方法
    @HystrixCommand
    public String ok(){
        int age = 10/0;
        String ok = paymentService.ok();
        return ok;
    }

    // 请求后睡眠指定时间再反馈
    @RequestMapping("/payment/timeout/{sleepTime}")
    // 注意: fallbackMethod指定的方法的参数列表要跟当前方法的参数列表的参数一样,否则会报错
    @HystrixCommand(fallbackMethod = "specialHystrixFallback",commandProperties = {
            // 设置最大的等待时间,超过这个时间获取反馈则认为是超时了
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String timeOut(@PathVariable("sleepTime") Integer sleepTime){
        String timeOut = paymentService.timeOut(sleepTime);
        return timeOut;
    }

    // 全局通用的Hystrix备选反馈
    public String globalHystrixFallback(){
        String logs = "全局通用的服务降级备选处理反馈！";
        log.info(logs);
        return logs;
    }


    // 特殊指定的Hystrix备选反馈
    public String specialHystrixFallback(Integer abc){
        String logs = "特殊指定的服务降级备选处理反馈！";
        log.info(logs);
        return logs;
    }

    @RequestMapping("/payment/breakout")
    public String breakOut(){
        return "测试服务宕机";
    }

}
