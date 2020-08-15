package org.zy.rpc.provider;

import org.zy.rpc.provider.publish.PublishService;
import org.zy.rpc.provider.service.HelloRPCServiceImpl;

import java.io.IOException;

/**
 * @author jogin
 * @date 2020.8.15
 *
 * Self-RPC ProviderApplication
 */
public class ProviderApplication {

    private static final int PORT=8080;

    public static void main(String[] args) {
        ProviderApplication.run();
    }

    public static void run(){
        try {
            PublishService.publish(new HelloRPCServiceImpl(),PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
