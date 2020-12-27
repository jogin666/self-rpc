package com.zy.self.rpc.sample.registry.registar;

import com.zy.self.rpc.sample.config.RpcConfig;
import com.zy.self.rpc.sample.registry.dto.ServiceRegistryParam;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Jong
 * @Date 2020/12/27 12:27
 * @Version 1.0
 */
public class RpcServiceRegistrar implements ServiceRegistrar, Runnable {

    /**
     * service name
     */
    public String serviceName;
    /**
     * registry address
     */
    private InetSocketAddress registryAddress;

    /**
     * discoverer service worker
     */
    private static final ExecutorService REGISTRY_SERVICE_DISCOVERY_WORKER = Executors.newFixedThreadPool(5);


    public RpcServiceRegistrar(String serviceName, InetSocketAddress registryAddress) {
        this.serviceName = serviceName;
        this.registryAddress = registryAddress;
    }

    /**
     * execute a registry-rpc-service task
     */
    @Override
    public void run() {
        REGISTRY_SERVICE_DISCOVERY_WORKER.execute(this::registry);
    }

    /**
     * registry service
     */
    @Override
    public void registry() {
        try (Socket socket = new Socket()) {
            socket.connect(registryAddress);
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            InetSocketAddress providerAddress = new InetSocketAddress(hostAddress, RpcConfig.PROVIDER_PORT);

            ServiceRegistryParam registryParam = new ServiceRegistryParam();
            registryParam.setProviderAddress(providerAddress.toString());
            registryParam.setServiceName(serviceName);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(registryParam);
            outputStream.flush();
            System.out.printf("Service provider successfully register service: [%s]\n", this.serviceName);
        } catch (Exception e) {
            System.err.printf("Service provider fail to establish a connection to the service registry: [%s]\n", e);
        }
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public InetSocketAddress getRegistryAddress() {
        return registryAddress;
    }

    public void setRegistryAddress(InetSocketAddress registryAddress) {
        this.registryAddress = registryAddress;
    }
}
