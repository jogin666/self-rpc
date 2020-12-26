package com.zy.self.rpc.sample.demo.producer.service;

import com.zy.self.rpc.sample.demo.service.SayHelloService;

/**
 * @Author Jong
 * @Date 2020/12/26 13:03
 * @Version 1.0
 */
public class SayHelloServiceImpl implements SayHelloService {

    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
