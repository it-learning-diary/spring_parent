package com.elvis.springcloud.dao;

import com.elvis.springcloud.model.Payment;
import org.apache.ibatis.annotations.Mapper;

public interface PaymentDao {
    // 添加
    public int addPayment(Payment payment);
    // 查找
    public Payment getPaymentById(Long id);
}
