package com.zy.self.rpc.sample.provider;

import com.zy.self.rpc.sample.registry.registar.RpcServiceRegistrar;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Jong
 * @Date 2020/12/27 12:42
 * @Version 1.0
 */
public class ServiceProvider {

    /**
     * register rpc-service task processing pool
     */
    private static final ExecutorService SERVICE_PUBLISH_TASK_HANDLER = Executors.newFixedThreadPool(3);
    /**
     * listen rpc-service task processing pool
     */
    private static final ExecutorService SERVICE_PROVIDE_TASK_HANDLER = Executors.newFixedThreadPool(3);

    /**
     * register and listen rpc-service request
     */
    public static <T> boolean provide(T service, InetSocketAddress registryAddress){
        // execute register-service task
        String serviceName = service.getClass().getInterfaces()[0].getCanonicalName();
        SERVICE_PUBLISH_TASK_HANDLER.submit(new RpcServiceRegistrar(serviceName,registryAddress));
        // execute call-service task
        SERVICE_PROVIDE_TASK_HANDLER.submit(new ServiceProviderTask(service));

        return true;
    }
}
