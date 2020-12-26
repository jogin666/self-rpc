package com.zy.self.rpc.provider;

import com.zy.self.rpc.transport.dto.RpcRequestParam;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @Author Jong
 * @Date 2020/12/26 12:31
 * @Version 1.0
 */
public class ProvideServiceTask<T> implements Runnable {

    /**
     *
     */
    private ServerSocket serverSocket;
    /**
     * rpc service
     */
    private T service;

    public ProvideServiceTask(T service, int port) {
        init(service, port);
    }

    /**
     * listen the port before publish service
     */
    private void init(T service, int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            this.service = service;
        } catch (Exception e) {
            System.err.printf("initial provide task occur a exception: {%s}", e);
        }
    }


    /**
     * call method,to invoke service
     */
    @Override
    public void run() {
        while (true) {
            try (Socket socket = serverSocket.accept()) {
                System.out.println("开始执行请求......");
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                RpcRequestParam requestParam = (RpcRequestParam) in.readObject();
                System.out.println("调用接口......");
                Object result = invoke(requestParam);

                out.writeObject(result);
                System.out.printf("接口调用结果: [%s]\n", result);
                out.flush();

                System.out.println("请求调用结束.......");
            } catch (Exception e) {
                System.err.printf("execute rpc request occurs a exception: {%s}", e);
                break;
            }
        }
    }

    /**
     * invoke real service
     */
    private Object invoke(RpcRequestParam requestParam) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object[] args = requestParam.getArgs();
        String className = requestParam.getClassName();
        String methodName = requestParam.getMethodName();
        Class<?>[] parameterTypes = requestParam.getParameterTypes();

        Class<?> clazz = Class.forName(className);
        Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
        return method.invoke(service, args);
    }
}

