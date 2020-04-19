package com.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String ok(){
        return Thread.currentThread().getName()+",请求ok服务一切正常";
    }

    @Override
    public String timeOut(Integer sleepTimes){
        int sleepTime = sleepTimes;
        try {
            Thread.sleep(sleepTime);
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
        }
        return Thread.currentThread().getName()+",请求timeOut服务正常";
    }

    @Override
    public String breakOut(Integer sleepTime) {
        return "测试服务器宕机";
    }
}
