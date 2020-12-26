package com.zy.self.rpc.sample.demo.producer;

import com.zy.self.rpc.provider.RpcServiceProvider;
import com.zy.self.rpc.sample.demo.producer.service.SayHelloServiceImpl;

/**
 * @Author Jong
 * @Date 2020/12/26 13:02
 * @Version 1.0
 */
public class ServiceProviderApplication {

    private static final int PORT = 8080;

    public static void main(String[] args) {
        ServiceProviderApplication.run();
    }

    public static void run() {
        RpcServiceProvider.publish(new SayHelloServiceImpl(), PORT);
    }
}
