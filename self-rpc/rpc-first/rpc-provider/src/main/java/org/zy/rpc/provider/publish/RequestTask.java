package org.zy.rpc.provider.publish;

import org.zy.rpc.service.model.RequestParam;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.concurrent.Callable;

/**
 * @author jogin
 * @date 2020.8.15
 *
 * Request Task
 */
public class RequestTask<T>  implements Callable {

    private Socket socket;
    private T service;

    public RequestTask(Socket socket,T service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public Object call() throws Exception {
        System.out.println("开始执行请求......");
        try (
            ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
        ){
            RequestParam requestParam =(RequestParam) in.readObject();

            System.out.println("调用接口......");
            Object result = invoke(requestParam);

            out.writeObject(result);
            System.out.printf("接口调用结果: [%s]\n",result);
            out.flush();

            System.out.println("请求调用结束.......");
        }
        return null;
    }

    //使用反射去调用
    public Object invoke(RequestParam requestParam) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object[] args = requestParam.getArgs();
        String className = requestParam.getClassName();
        String methodName = requestParam.getMethodName();
        Class[] parameterTypes = requestParam.getParameterTypes();

        Class<?> clazz = Class.forName(className);
        Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
        return method.invoke(service,args);
    }
}
