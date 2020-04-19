package com.elvis.springcloud.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class CustomIRuleImpl implements CustomIRule
{
    // 该注解是用来获取注册在注册中心的消费者服务的信息,它要起作用,还需要在主启动类表明@EnableDiscoveryClient注解
    @Autowired
    private DiscoveryClient discoveryClient;

    private static  AtomicInteger serviceIndex = null;

    public CustomIRuleImpl(){
        serviceIndex =  new AtomicInteger(0);
    }

    // 获取特定服务的集群列表
    @Override
    public List<ServiceInstance> getInstances(String serviceId) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        return instances;
    }

    // 根据算法获取集群中本次访问的服务
    public ServiceInstance getServerInstance(String serverId){
        // 列出当前服务的所有集群
        List<ServiceInstance> instances = this.getInstances(serverId);
        if(null == instances || instances.isEmpty()){
            log.error("获取集群服务列表失败");
            return null;
        }
        ServiceInstance chooseServer = null;
        do{
            // 获取本次访问的服务在集群中的下标
            Integer index = getAndIncrement();
            log.info("请求的次数:"+index);
            // 从集群中获取到服务
            // 轮询算法基础: 需要访问的服务下标 = 请求的次数 % 当前服务集群的总数
            int serverIndex = index % instances.size();
            chooseServer = instances.get(serverIndex);
        }while (chooseServer == null);
        return chooseServer;
    }

    // 获取客户端访问的次数
    public final  Integer getAndIncrement(){
        int current;
        int next;
        do {
            // 如果访问次数超过Integer取值的最大值,则重新置为0
            // 注意: 每次重启服务后,访问次数都会从0开始
            current =  serviceIndex.get();
            next = current >= Integer.MAX_VALUE ? 0 : (current+1);
            // 通过自旋锁和线程安全原子类保证线程安全(这个知识如果有需要会在后面的课程专门开启一系列的文章进行解读)
            // 每次都进行比较: 看当前的值是否等于预期的值,如果相等,则设置为next，并返回next，不然就一直自旋,直到拿到符合的值位置
        }while (!serviceIndex.compareAndSet(current,next));
        return  next;
    }

}
