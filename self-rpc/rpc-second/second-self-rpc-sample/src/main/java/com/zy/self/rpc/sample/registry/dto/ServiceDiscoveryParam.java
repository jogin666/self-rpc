package com.zy.self.rpc.sample.registry.dto;

import java.io.Serializable;

/**
 * @Author Jong
 * @Date 2020/12/27 12:22
 * @Version 1.0
 */
public class ServiceDiscoveryParam implements Serializable {

    /**
     * service name
     */
    private String serviceName;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String toString() {
        return "ServiceDiscoveryParam{" +
                "serviceName='" + serviceName + '\'' +
                '}';
    }
}
