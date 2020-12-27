package com.zy.self.rpc.sample.registry.dto;

import java.io.Serializable;

/**
 * @Author Jong
 * @Date 2020/12/27 12:23
 * @Version 1.0
 */
public class ServiceRegistryParam implements Serializable {

    /**
     * service name
     */
    private String serviceName;
    /**
     * service provider address
     */
    private String providerAddress;

    public String getProviderAddress() {
        return providerAddress;
    }

    public void setProviderAddress(String providerAddress) {
        this.providerAddress = providerAddress;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String toString() {
        return "ServiceRegistryParam{" +
                "serviceName='" + serviceName + '\'' +
                ", providerAddress='" + providerAddress + '\'' +
                '}';
    }
}
