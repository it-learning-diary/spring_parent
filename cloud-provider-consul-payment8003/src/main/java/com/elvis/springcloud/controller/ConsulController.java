package com.elvis.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ConsulController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/payment/consul/get")
    public String consulTest(){
        String str = "ConsulController consulTest"+port+ UUID.randomUUID();
        return str;
    }
}
