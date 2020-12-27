package com.zy.self.rpc.sample.registry.discoverer;

import com.zy.self.rpc.sample.config.RpcConfig;
import com.zy.self.rpc.sample.registry.dto.ServiceDiscoveryParam;
import com.zy.self.rpc.sample.registry.holder.ServiceHolder;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Jong
 * @Date 2020/12/27 12:38
 * @Version 1.0
 */
public class RegistryServiceDiscoverer implements ServiceDiscoverer{

    /**
     * rpc service holder
     */
    private final ServiceHolder serviceHolder = new ServiceHolder();
    /**
     * worker thread ,registry rpc service
     */
    private static final ExecutorService REGISTRY_WORKER = Executors.newFixedThreadPool(5);

    /**
     * execute a discoverer-rpc-service task
     */
    @Override
    public void discovery() {
        try (ServerSocket serverSocket = new ServerSocket(RpcConfig.DISCOVERY_PORT)) {
            while (true) {
                // TODO Socket 未关闭
                Socket socket = serverSocket.accept();
                REGISTRY_WORKER.execute(() -> this.discovery(socket));
            }
        } catch (Exception e) {
            System.err.printf("service-registry listen the service-discovery-port: [%d],but occur a error: [%s]\n", RpcConfig.REGISTRY_PORT, e);
        }
    }

    /**
     * for service registry looking for services
     */
    private void discovery(Socket socket) {
        try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {

            ServiceDiscoveryParam discoveryParam = (ServiceDiscoveryParam) inputStream.readObject();
            System.out.printf("Service registry receive rpc request and lookup service：[%s]\n", discoveryParam.toString());
            Set<String> providerAddress = serviceHolder.discovery(discoveryParam.getServiceName());

            if (providerAddress == null || providerAddress.size() == 0) {
                System.out.printf("Service registry has not found the service：[%s]\n", discoveryParam.getServiceName());
                return;
            }
            Random random = new Random();
            int index = random.nextInt(providerAddress.size());
            Object[] addressArray = providerAddress.toArray();

            outputStream.writeObject(addressArray[index]);
            System.out.printf("registry lookup service：[%s]\n", addressArray[index]);
        } catch (Exception e) {
            System.err.printf("registry lookup service has occur a error：[%s]\n", e.getMessage());
        }
    }
}
