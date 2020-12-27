package com.zy.self.rpc.sample.transport.handler;

import com.zy.self.rpc.sample.transport.dto.RequestParam;
import com.zy.self.rpc.sample.transport.request.RpcRequest;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author Jong
 * @Date 2020/12/27 12:15
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
        RequestParam requestParam = new RequestParam();
        requestParam.setArgs(args);
        requestParam.setClassName(method.getDeclaringClass().getName());
        requestParam.setParameterTypes(method.getParameterTypes());
        requestParam.setMethodName(method.getName());
        return handler(requestParam);
    }

    /**
     * return result after invoke rpc request
     */
    private Object handler(RequestParam requestParam) throws IOException {
        RpcRequest rpcRequest = new RpcRequest(hostAddress, port);
        System.out.print("执行请求.....\n");
        System.out.printf("请求的数据：[%s]\n", requestParam.toString());
        Object result = rpcRequest.sendRequest(requestParam);
        System.out.println("执行请求结束......");
        return result;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
