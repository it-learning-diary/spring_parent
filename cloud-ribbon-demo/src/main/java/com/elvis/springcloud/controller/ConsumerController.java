package com.elvis.springcloud.controller;

import com.elvis.springcloud.custom.CustomIRuleImpl;
import com.elvis.springcloud.model.CommonResult;
import com.elvis.springcloud.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;
    //private final String url = "http://localhost:8001/payment/get/";
    // 此处直接使用服务的名称，而不需要直接写死请求路径
    private final String url = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private CustomIRuleImpl customIRule;

    @RequestMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getConsumerPayment(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(url +"/payment/get/"+id, CommonResult.class);
        // 获取某个服务的集群
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        return entity.getBody();
    }

    @RequestMapping("/ribbon/demo")
    public String testCustomRoundRibbon(){
        ServiceInstance serverInstance = customIRule.getServerInstance("cloud-payment-service");
        if(null != serverInstance){
            String result = restTemplate.getForObject(serverInstance.getUri()+"/payment/test/ribbon", String.class);
            return result;
        }
        return "测试失败";
    }
}
