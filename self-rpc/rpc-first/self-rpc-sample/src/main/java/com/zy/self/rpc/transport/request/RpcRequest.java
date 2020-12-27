package com.zy.self.rpc.transport.request;

import com.zy.self.rpc.transport.dto.RpcRequestParam;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Author Jong
 * @Date 2020/12/26 12:39
 * @Version 1.0
 */
public class RpcRequest {


    /**
     * provider address
     */
    private final String hostAddress;
    /**
     * listen port
     */
    private final int port;

    public RpcRequest(String hostAddress, int port) {
        this.hostAddress = hostAddress;
        this.port = port;
    }

    /**
     * return a result which send rpc request
     */
    public Object sendRequest(RpcRequestParam requestParam) throws IOException {
        try (
                Socket socket = new Socket(hostAddress, port);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {
            out.writeObject(requestParam);
            out.flush();

            return in.readObject();
        } catch (ClassNotFoundException e) {
            System.err.printf("send a rpc request has occur a error: {%s}",e);
        }
        return null;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public int getPort() {
        return port;
    }
}
