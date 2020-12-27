package com.zy.self.rpc.sample.demo.second.producer.service;

import com.zy.self.rpc.sample.demo.second.service.SayHelloService;


/**
 * @Author Jong
 * @Date 2020/12/27 12:56
 * @Version 1.0
 */
public class SayHelloServiceImpl implements SayHelloService {

    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
