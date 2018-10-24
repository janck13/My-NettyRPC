package com.jsj.config;

import com.jsj.nettyrpc.common.client.RpcProxy;
import com.jsj.nettyrpc.registry.ServiceDiscovery;
import com.jsj.nettyrpc.registry.impl.ZookeeperDiscovery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RpcConfig {

    private String host;

    @Bean
    public ServiceDiscovery initServiceDiscovery() {
        host = "127.0.0.1";
        String port = "2181";
        return new ZookeeperDiscovery(host + ":" + port);
    }

    @Bean
    public RpcProxy initRpcProxy(@Autowired ServiceDiscovery serviceDiscovery) {
        return new RpcProxy(serviceDiscovery);
    }
}
