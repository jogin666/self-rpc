package com.zy.self.rpc.sample.transport.dto;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @Author Jong
 * @Date 2020/12/27 12:02
 * @Version 1.0
 */
public class RequestParam implements Serializable {
    /**
     * interface type
     */
    private String className;
    /**
     * invoke method
     */
    private String methodName;
    /**
     * method parameter
     */
    private Object[] args;
    /**
     * method parameter type
     */
    private Class<?>[] parameterTypes;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }


    @Override
    public String toString() {
        return "RequestParam{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", args=" + Arrays.toString(args) +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                '}';
    }

}
