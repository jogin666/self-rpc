package com.zy.self.rpc.sample.registry.discoverer;

import com.zy.self.rpc.sample.registry.dto.ServiceDiscoveryParam;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Author Jong
 * @Date 2020/12/27 12:37
 * @Version 1.0
 */
public class RpcServiceDiscoverer implements ServiceDiscoverer{
    /**
     * service provider address
     */
    private String providerAddress;
    /**
     * service registry address
     */
    private InetSocketAddress registryAddress;
    /**
     * rpc service name
     */
    private String serviceName;

    /**
     * for consumer looking for rpc service
     */
    @Override
    public void discovery() {
        try (Socket socket = new Socket()) {
            socket.connect(registryAddress);

            ServiceDiscoveryParam discoveryParam = new ServiceDiscoveryParam();
            discoveryParam.setServiceName(serviceName);
            System.out.printf("consumer try obtaining rpc service: [%s] from registry", serviceName);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(discoveryParam);

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            this.providerAddress = (String) inputStream.readObject();
            System.out.printf("consumer obtain a rpc service: [%s]", serviceName);

        } catch (Exception e) {
            System.err.printf("consumer try obtaining rpc service: [%s] from registry,but has occur a error: [%s]", serviceName, e);
        }
    }

    public String discoveryService(String serviceName, InetSocketAddress registryAddress) {
        init(serviceName, registryAddress);
        this.discovery();
        return providerAddress;
    }

    public void init(String serviceName, InetSocketAddress registryAddress) {
        this.registryAddress = registryAddress;
        this.serviceName = serviceName;
    }

    public String getProviderAddress() {
        return providerAddress;
    }

    public void setProviderAddress(String providerAddress) {
        this.providerAddress = providerAddress;
    }

    public InetSocketAddress getRegistryAddress() {
        return registryAddress;
    }

    public void setRegistryAddress(InetSocketAddress registryAddress) {
        this.registryAddress = registryAddress;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
