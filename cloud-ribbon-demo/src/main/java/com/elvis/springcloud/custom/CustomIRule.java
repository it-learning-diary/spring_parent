package com.elvis.springcloud.custom;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 *  负载均衡算法标准(面向接口开发)
 */
public interface CustomIRule {

    // 获取服务的集群
    List<ServiceInstance>  getInstances(String serviceId);

}
