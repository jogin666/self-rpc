package com.zy.self.rpc.sample.registry.holder;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author Jong
 * @Date 2020/12/27 12:24
 * @Version 1.0
 */
public class ServiceHolder {

    /**
     * rpc service registry
     */
    private static final ConcurrentHashMap<String, Set<String>> SERVICE_REGISTRY_MAP = new ConcurrentHashMap<>(32);

    /**
     * registry rpc service
     */
    public void registry(String serviceName, String address) {
        Set<String> serviceAddress = SERVICE_REGISTRY_MAP.get(serviceName);
        if (serviceAddress == null) {
            HashSet<String> addressSet = new HashSet<>();
            addressSet.add(address);
            SERVICE_REGISTRY_MAP.putIfAbsent(serviceName, addressSet);
            System.out.printf("successfully registry service: [%s]\n",serviceName);
        }else if (serviceAddress != null && !serviceAddress.contains(address)) {
            serviceAddress.add(address);
            System.out.printf("successfully registry service: [%s]\n",serviceName);
        }else{
            System.out.printf("service has registry: [%s]\n",serviceName);
        }
    }

    /**
     * discoverer service
     */
    public Set<String> discovery(String serviceName) {
        Set<String> serviceAddress = SERVICE_REGISTRY_MAP.get(serviceName);
        return serviceAddress;
    }
}
