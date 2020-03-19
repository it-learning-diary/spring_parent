package com.elvis.springcloud.service;

import com.elvis.springcloud.model.Payment;

public interface PaymentService {
    // 添加
    public int addPayment(Payment payment);
    // 查找
    public Payment getPaymentById(Long id);
}
