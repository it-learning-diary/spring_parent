package com.elvis.springcloud.controller;

import com.elvis.springcloud.feign.OrderFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FeignOrderController {

    @Autowired
    private OrderFeign orderFeign;

    @RequestMapping("/consumer/payment/test/ribbon")
    public String testCustomer(){
        String s = orderFeign.testCustomer();
        log.info("本次提供服务的服务端端口号为:"+s);
        return s;
    }

    @RequestMapping("/payment/test/ribbon/timeout")
    public String testCustomerTimeout(){
        return orderFeign.testCustomerTimeout();
    }
}
