package com.zy.self.rpc.sample.config;

/**
 * @Author Jong
 * @Date 2020/12/27 12:00
 * @Version 1.0
 */
public class RpcConfig {
    /**
     * service provider port
     */
    public static final int PROVIDER_PORT = 20880;
    /**
     * service register port
     */
    public static final int REGISTRY_PORT = 9876;
    /**
     * service discovery port
     */
    public static final int DISCOVERY_PORT = 9777;
    /**
     * service registry address
     */
    public static final String REGISTRY_ADDRESS = "127.0.0.1";
}
