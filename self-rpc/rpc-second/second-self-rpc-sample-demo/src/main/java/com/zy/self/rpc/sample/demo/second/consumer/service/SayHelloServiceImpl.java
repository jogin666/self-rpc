package com.zy.self.rpc.sample.demo.second.consumer.service;

import com.zy.self.rpc.sample.config.RpcConfig;
import com.zy.self.rpc.sample.demo.second.service.SayHelloService;
import com.zy.self.rpc.sample.proxy.RpcClientProxy;
import com.zy.self.rpc.sample.registry.discoverer.RpcServiceDiscoverer;

import java.net.InetSocketAddress;

/**
 * @Author Jong
 * @Date 2020/12/27 12:57
 * @Version 1.0
 */
public class SayHelloServiceImpl implements SayHelloService {

    private RpcServiceDiscoverer discoveryService = new RpcServiceDiscoverer();

    @Override
    public String sayHello(String name) {
        return getRemoteService(name);
    }

    private String getRemoteService(String name){
        String[] address = lookupProviderAddress();
        SayHelloService service = RpcClientProxy.proxy(SayHelloService.class, address[0], Integer.parseInt(address[1]));
        return service.sayHello(name);
    }

    private String[] lookupProviderAddress(){
        InetSocketAddress inetSocketAddress = new InetSocketAddress(RpcConfig.REGISTRY_ADDRESS, RpcConfig.DISCOVERY_PORT);
        String providerAddress = discoveryService.discoveryService(SayHelloService.class.getCanonicalName(),inetSocketAddress);
        if (providerAddress.startsWith("/")){
            providerAddress = providerAddress.substring(1);
        }
        return providerAddress.split(":");
    }
}
