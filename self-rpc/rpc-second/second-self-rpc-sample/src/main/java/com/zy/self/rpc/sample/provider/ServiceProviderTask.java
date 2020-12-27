package com.zy.self.rpc.sample.provider;

import com.zy.self.rpc.sample.config.RpcConfig;
import com.zy.self.rpc.sample.transport.dto.RequestParam;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Jong
 * @Date 2020/12/27 12:43
 * @Version 1.0
 */
public class ServiceProviderTask<T> implements Runnable {

    /**
     *
     */
    private ServerSocket serverSocket;
    /**
     * rpc service
     */
    private final T service;
    /**
     * invoke rpc-service task processing pool
     */
    private final ExecutorService SERVICE_PROVIDE_WORKER = Executors.newFixedThreadPool(5);

    public ServiceProviderTask(T service) {
        init();
        this.service = service;
    }

    private void init() {
        try {
            this.serverSocket = new ServerSocket(RpcConfig.PROVIDER_PORT);
            System.out.printf("service provider listen port: [%d]\n", RpcConfig.PROVIDER_PORT);
        } catch (Exception e) {
            System.err.printf("service provider listen port: [%d], occur a error\n", RpcConfig.PROVIDER_PORT, e);
        }
    }

    /**
     * call method,to invoke service
     */
    @Override
    public void run() {
        while (true) {
            try (Socket socket = serverSocket.accept()) {
                invokeRpcService(socket);
                // 交给线程池执行之后，socket 被关掉 TODO 使用线程池执行
//                SERVICE_PROVIDE_WORKER.execute(() -> invokeRpcService(socket));
            } catch (Exception e) {
                System.err.printf("service provider fail to establish a connection to the service registry: [%s]\n", e);
            }
        }

    }

    /**
     * invoke rpc service
     */
    private void invokeRpcService(Socket socket) {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            RequestParam requestParam = (RequestParam) in.readObject();
            System.out.printf("service provider call the service: [%s]\n", requestParam.getClassName());
            Object result = invoke(requestParam);
            out.writeObject(result);
            System.out.printf("the result of service provider call the service: [%s]\n", result);
            out.flush();
        } catch (Exception e) {
            System.err.printf("invoke rpc service has occur a error: [%s]\n", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * return a result that send rpc request
     */
    private Object invoke(RequestParam requestParam) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object[] args = requestParam.getArgs();
        String className = requestParam.getClassName();
        String methodName = requestParam.getMethodName();
        Class<?>[] parameterTypes = requestParam.getParameterTypes();
        Class<?> clazz = Class.forName(className);
        Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
        return method.invoke(service, args);
    }
}
