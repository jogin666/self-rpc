package org.zy.rpc.provider.publish;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jogin
 * @date 2020.8.15
 *
 * publish service
 */
public class PublishService {

    public static <T> void publish(T service,int port) throws IOException {
        ServerSocket server=new ServerSocket(port);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        while (true){
            Socket socket = server.accept();
            executorService.submit(new RequestTask<T>(socket,service));
        }
    }
}
