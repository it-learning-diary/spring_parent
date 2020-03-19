package com.elvis.springcloud;

import com.elvis.springcloud.model.CommonResult;
import com.elvis.springcloud.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;
    //private final String url = "http://localhost:8001/payment/get/";
    // 此处直接使用服务的名称，而不需要直接写死请求路径
    private final String url = "http://CLOUD-PAYMENT-SERVICE";
    @RequestMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getConsumerPayment(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(url +"/payment/get/"+id, CommonResult.class);
        return entity.getBody();
    }
}
