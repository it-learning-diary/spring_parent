package com.elvis.springcloud.dao;

import com.elvis.springcloud.model.Payment;

public interface PaymentDao {
    // 添加
    public int addPayment(Payment payment);
    // 查找
    public Payment getPaymentById(Long id);
}
