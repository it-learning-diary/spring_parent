package com.springcloud.controller;

import com.springcloud.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;
    @RequestMapping("/consumer/payment/ok")
    public String ok(){
        String ok = consumerService.ok();
        //int i = 10/0;
        log.info(Thread.currentThread().getName()+",消费端获取到的信息:"+ok);
        return ok;
    }

    @RequestMapping("/consumer/payment/timeout/{sleepTime}")
    public String timeOut(@PathVariable("sleepTime") Integer sleepTime){
        String s = consumerService.timeOut(sleepTime);
        log.info(Thread.currentThread().getName()+",消费端获取到的信息:"+s);
        return s;
    }


    @RequestMapping("/consumer/payment/breakout")
    public String breakOut(){
        try{
            String s = consumerService.breakOut();
            return s;
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return "breakOut请求失败";
    }

}
