package org.zy.rpc.consumer.proxy;


import java.lang.reflect.Proxy;

/**
 * @author jogin
 * @date 2020.8.15
 *
 * Self-RPC Client Request Proxy
 */
public class RPCRequestProxyClient {

    public static <T> T proxy(Class<T> interfaceClass,String hostAddress,int port){
        T instance =(T) Proxy.newProxyInstance(interfaceClass.getClassLoader()
                            , new Class[]{interfaceClass}
                            , new RPCHandler(hostAddress,port));
        return instance;
    }
}
