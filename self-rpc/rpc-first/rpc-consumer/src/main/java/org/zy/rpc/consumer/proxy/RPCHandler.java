package org.zy.rpc.consumer.proxy;

import org.zy.rpc.consumer.request.RPCRequest;
import org.zy.rpc.service.model.RequestParam;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author jogin
 * @date 2020.8.15
 *
 */
public class RPCHandler implements InvocationHandler {

    private String hostAddress;
    private int port;

    public RPCHandler(String hostAddress, int port) {
        this.hostAddress = hostAddress;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RequestParam requestParam = new RequestParam();
        requestParam.setArgs(args);
        requestParam.setClassName(method.getDeclaringClass().getName());
        requestParam.setParameterTypes(method.getParameterTypes());
        requestParam.setMethodName(method.getName());
        return handler(requestParam);
    }

    public Object handler(RequestParam requestParam) throws IOException {
        RPCRequest rpcRequest = new RPCRequest(hostAddress, port);
        System.out.printf("执行请求.....");
        System.out.printf("\n请求的数据：[%s]\n",requestParam.toString());
        Object result = rpcRequest.sendRequest(requestParam);
        System.out.println("执行请求结束......");
        return result;
    }
}
