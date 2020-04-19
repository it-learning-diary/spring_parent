package com.elvis.springcloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface OrderFeign {
    @RequestMapping("/payment/test/ribbon")
    public String testCustomer();

    @RequestMapping("/payment/test/ribbon/timeout")
    public String testCustomerTimeout();
}
