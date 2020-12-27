package com.zy.self.rpc.sample.demo.second.registry;

import com.zy.self.rpc.sample.registry.ServiceRegistryStarter;

/**
 * @Author Jong
 * @Date 2020/12/27 12:54
 * @Version 1.0
 */
public class ServiceRegistryApplication {

    public static void main(String[] args) {
        ServiceRegistryApplication.run();
    }

    private static void run(){
        ServiceRegistryStarter.start();
    }
}
