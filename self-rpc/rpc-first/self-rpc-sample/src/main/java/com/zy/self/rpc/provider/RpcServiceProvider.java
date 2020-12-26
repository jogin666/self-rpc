package com.zy.self.rpc.provider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Jong
 * @Date 2020/12/26 12:30
 * @Version 1.0
 */

public class RpcServiceProvider {

    /**
     * task processing pool
     */
    private static final ExecutorService TASK_HANDLER = Executors.newFixedThreadPool(3);

    /**
     * publish rpc service
     */
    public static <T> void publish(T service, int port) {
        TASK_HANDLER.submit(new ProvideServiceTask<>(service, port));
    }
}
