package com.zy.self.rpc.sample.demo.second.consumer;

import com.zy.self.rpc.sample.demo.second.consumer.service.SayHelloServiceImpl;
import com.zy.self.rpc.sample.demo.second.service.SayHelloService;

/**
 * @Author Jong
 * @Date 2020/12/27 12:56
 * @Version 1.0
 */
public class ServiceConsumerApplication {

    public static void main(String[] args) {
        ServiceConsumerApplication.run();
    }

    private static void run(){
        for (int i = 0; i < 3; i++) {
            SayHelloService service = new SayHelloServiceImpl();
            System.out.printf("rpc processing: [%s]\n",service.sayHello("self rpc"));
        }
    }
}
