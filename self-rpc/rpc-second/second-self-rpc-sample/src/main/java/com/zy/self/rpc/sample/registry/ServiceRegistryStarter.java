package com.zy.self.rpc.sample.registry;

import com.zy.self.rpc.sample.config.RpcConfig;
import com.zy.self.rpc.sample.registry.discoverer.RegistryServiceDiscoverer;
import com.zy.self.rpc.sample.registry.registar.RegistryServiceRegistrar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Jong
 * @Date 2020/12/27 12:47
 * @Version 1.0
 */
public class ServiceRegistryStarter {
    /**
     * service discoverer executor
     */
    private static final ExecutorService SERVICE_DISCOVERY_STARTER = Executors.newFixedThreadPool(1);
    /**
     * service registry executor
     */
    private static final ExecutorService SERVICE_REGISTRY_STARTER = Executors.newFixedThreadPool(1);
    /**
     * registry service discover
     */
    private static final RegistryServiceDiscoverer serviceDiscovery = new RegistryServiceDiscoverer();
    /**
     * service registry
     */
    private static final RegistryServiceRegistrar serviceRegistry = new RegistryServiceRegistrar();

    /**
     * start service registry, so registry and discoverer rpc service
     */
    public static void start() {

        SERVICE_REGISTRY_STARTER.execute(serviceRegistry::registry);
        System.out.printf("Service registry successfully startup and listen service-register-port： [%d]\n", RpcConfig.REGISTRY_PORT);
        SERVICE_DISCOVERY_STARTER.execute(serviceDiscovery::discovery);
        System.out.printf("Service registry listen service-discovery-port: [%d]\n",RpcConfig.DISCOVERY_PORT);
    }

}
