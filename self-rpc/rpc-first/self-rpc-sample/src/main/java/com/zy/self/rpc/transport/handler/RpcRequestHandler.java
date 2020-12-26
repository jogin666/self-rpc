package com.zy.self.rpc.transport.handler;

import com.zy.self.rpc.transport.dto.RpcRequestParam;
import com.zy.self.rpc.transport.request.RpcRequest;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author Jong
 * @Date 2020/12/26 12:37
 * @Version 1.0
 */
public class RpcRequestHandler implements InvocationHandler {

    /**
     * provider address
     */
    private String hostAddress;
    /**
     * listen port
     */
    private int port;

    public RpcRequestHandler(String hostAddress, int port) {
        this.hostAddress = hostAddress;
        this.port = port;
    }

    /**
     * intercept target method,to execute rpc request
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequestParam requestParam = new RpcRequestParam();
        requestParam.setArgs(args);
        requestParam.setClassName(method.getDeclaringClass().getName());
        requestParam.setParameterTypes(method.getParameterTypes());
        requestParam.setMethodName(method.getName());
        return handler(requestParam);
    }

    /**
     * return result after invoke rpc request
     */
    private Object handler(RpcRequestParam requestParam) throws IOException {
        RpcRequest rpcRequest = new RpcRequest(hostAddress, port);
        System.out.printf("执行请求.....");
        System.out.printf("\n请求的数据：[%s]\n", requestParam.toString());
        Object result = rpcRequest.sendRequest(requestParam);
        System.out.println("执行请求结束......");
        return result;
    }
}
