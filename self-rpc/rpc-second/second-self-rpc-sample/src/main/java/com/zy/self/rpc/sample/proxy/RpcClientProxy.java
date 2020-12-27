package com.zy.self.rpc.sample.proxy;

import com.zy.self.rpc.sample.transport.handler.RpcRequestHandler;

import java.lang.reflect.Proxy;

/**
 * @Author Jong
 * @Date 2020/12/27 12:18
 * @Version 1.0
 */
public class RpcClientProxy {

    /**
     * return a proxy of special interface
     */
    @SuppressWarnings("unchecked")
    public static <T> T proxy(Class<T> interfaceClass, String hostAddress, int port) {
        T instance = (T) Proxy.newProxyInstance(interfaceClass.getClassLoader()
                , new Class[]{interfaceClass}
                , new RpcRequestHandler(hostAddress, port));
        return instance;
    }

}
