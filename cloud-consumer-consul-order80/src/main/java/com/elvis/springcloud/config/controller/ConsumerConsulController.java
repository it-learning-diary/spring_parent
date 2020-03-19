package com.elvis.springcloud.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerConsulController {

    @Autowired
    private RestTemplate restTemplate;
    private String url= "http://cloud-payment-service";

    @RequestMapping("consumer/payment/get/{id}")
    public String consulOrderTest(@PathVariable("id") Long id){
        String forObject = restTemplate.getForObject(url + "/payment/get/"+id, String.class);
        int a =1/0;
        return forObject;
    }
}
