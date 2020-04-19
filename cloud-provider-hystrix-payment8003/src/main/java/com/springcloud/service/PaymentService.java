package com.springcloud.service;

public interface PaymentService {
    String ok();
    String timeOut(Integer sleepTime);
    String breakOut(Integer sleepTime);
}
