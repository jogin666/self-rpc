package org.zy.rpc.consumer;

import org.zy.rpc.consumer.proxy.RPCRequestProxyClient;
import org.zy.rpc.service.service.HelloRPCService;

/**
 * @author jogin
 * @date 2020.8.15
 *
 * Self-RPC Consumer
 */
public class ConsumerApplication {

    private static final String HOTS_ADDRESS="127.0.0.1";
    private static final int PORT=8080;

    public static void main(String[] args) {
        ConsumerApplication.run();
    }

    public static void run(){
        HelloRPCService service= RPCRequestProxyClient.proxy(HelloRPCService.class,HOTS_ADDRESS,PORT);
        String result = service.sayHello("Self RPC");
        System.out.printf("result: [%s]",result);
    }
}
