package com.zy.self.rpc.proxy;

import com.zy.self.rpc.transport.handler.RpcRequestHandler;

import java.lang.reflect.Proxy;

/**
 * @Author Jong
 * @Date 2020/12/26 12:35
 * @Version 1.0
 */
public class RpcServiceProxy {

    /**
     * return a proxy of special interface
     */
    @SuppressWarnings("unchecked")
    public static <T> T proxy(Class<T> interfaceClass, String hostAddress, int port) {
        return  (T) Proxy.newProxyInstance(interfaceClass.getClassLoader()
                , new Class[]{interfaceClass}
                , new RpcRequestHandler(hostAddress, port));
    }
}
