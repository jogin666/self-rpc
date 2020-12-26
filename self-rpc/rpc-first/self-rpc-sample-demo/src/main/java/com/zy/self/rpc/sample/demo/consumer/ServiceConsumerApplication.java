package com.zy.self.rpc.sample.demo.consumer;

import com.zy.self.rpc.proxy.RpcServiceProxy;
import com.zy.self.rpc.sample.demo.service.SayHelloService;

/**
 * @Author Jong
 * @Date 2020/12/26 13:01
 * @Version 1.0
 */
public class ServiceConsumerApplication {

    private static final String HOTS_ADDRESS = "127.0.0.1";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        ServiceConsumerApplication.run();
    }

    public static void run() {
        SayHelloService service = RpcServiceProxy.proxy(SayHelloService.class, HOTS_ADDRESS, PORT);
        String result = service.sayHello("self rpc");
        System.out.printf("result: [%s]\n", result);
    }
}
