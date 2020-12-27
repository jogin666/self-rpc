package com.zy.self.rpc.sample.registry.registar;

import com.zy.self.rpc.sample.config.RpcConfig;
import com.zy.self.rpc.sample.registry.dto.ServiceRegistryParam;
import com.zy.self.rpc.sample.registry.holder.ServiceHolder;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Jong
 * @Date 2020/12/27 12:29
 * @Version 1.0
 */
public class RegistryServiceRegistrar implements ServiceRegistrar {

    /**
     * service holder
     */
    private final ServiceHolder serviceHolder = new ServiceHolder();

    /**
     * worker thread,discoverer rpc service
     */
    private static final ExecutorService DISCOVERY_WORKER = Executors.newFixedThreadPool(5);

    /**
     * execute a registry-rpc-service task
     */
    @Override
    public void registry() {

        try (ServerSocket serverSocket = new ServerSocket(RpcConfig.REGISTRY_PORT)) {
            while (true) {
                // TODO Socket 未关闭
                Socket socket = serverSocket.accept();
                DISCOVERY_WORKER.execute(() -> this.registry(socket));
            }
        } catch (Exception e) {
            System.err.printf("service-registry listen the service-registry-port: [%d],but occur a error: [%s]\n", RpcConfig.REGISTRY_PORT, e);
        }
    }

    /**
     * registry rpc service
     */
    private void registry(Socket socket) {
        try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
            ServiceRegistryParam registryParams = (ServiceRegistryParam) inputStream.readObject();
            System.out.printf("service-registry receive a rpc-service-registry-message: [%s]\n", registryParams.toString());
            serviceHolder.registry(registryParams.getServiceName(), registryParams.getProviderAddress());
        } catch (Exception e) {
            System.err.printf("service-registry try to register rpc-service,but occur a error: [%s]\n", e);
        }
    }
}
