package org.zy.rpc.service.model;

import java.io.Serializable;
import java.util.Arrays;

public class RequestParam implements Serializable {

    private String className;
    private String methodName;
    private Object[] args;
    private Class[]  parameterTypes;

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

    public Class[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }


    @Override
    public String toString() {
        return "RequestModel{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", args=" + Arrays.toString(args) +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                '}';
    }
}
