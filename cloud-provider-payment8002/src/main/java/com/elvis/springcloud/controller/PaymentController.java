package com.elvis.springcloud.controller;

import com.elvis.springcloud.model.CommonResult;
import com.elvis.springcloud.model.Payment;
import com.elvis.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;
    // 添加
    @PostMapping("payment/add")
    public CommonResult<Integer> addPayment(@RequestBody Payment payment){
        int i = paymentService.addPayment(payment);
        if(i > 0){
            return new CommonResult<>("0","success",i);
        }
        return new CommonResult<>("0","fail");
    }
    // 查找
    @GetMapping("payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment paymentById = paymentService.getPaymentById(id);
        if(null != paymentById){
            return new CommonResult<>("0","success"+port,paymentById);
        }
        return new CommonResult<>("0","fail");
    }

    @RequestMapping("/payment/test/ribbon")
    public String testCustomer(){
        return port;
    }

    @RequestMapping("/payment/test/ribbon/timeout")
    public String testCustomerTimeout(){
        try{
            Thread.sleep(3000);
        }catch (Exception e){
            log.info(e.getLocalizedMessage());
        }
        return port;
    }
}
