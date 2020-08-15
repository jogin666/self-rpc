package org.zy.rpc.provider.service;

import org.zy.rpc.service.service.HelloRPCService;

/**
 * @author jogin
 * @date 2020.8.15
 */
public class HelloRPCServiceImpl implements HelloRPCService {

    @Override
    public String sayHello(String name) {
        return "Hello "+name;
    }
}
