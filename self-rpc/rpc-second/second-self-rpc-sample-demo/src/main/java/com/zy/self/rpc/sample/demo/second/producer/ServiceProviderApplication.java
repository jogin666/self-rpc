package com.zy.self.rpc.sample.demo.second.producer;

import com.zy.self.rpc.sample.config.RpcConfig;
import com.zy.self.rpc.sample.demo.second.producer.service.SayHelloServiceImpl;
import com.zy.self.rpc.sample.provider.ServiceProvider;

import java.net.InetSocketAddress;

/**
 * @Author Jong
 * @Date 2020/12/27 12:55
 * @Version 1.0
 */
public class ServiceProviderApplication {

    public static void main(String[] args) {
        ServiceProviderApplication.run();
    }

    private static void run(){
        InetSocketAddress address = new InetSocketAddress(RpcConfig.REGISTRY_ADDRESS, RpcConfig.REGISTRY_PORT);
        ServiceProvider.provide(new SayHelloServiceImpl(),address);
    }
}
