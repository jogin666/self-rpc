package com.zy.self.rpc.sample.transport.request;

import com.zy.self.rpc.sample.transport.dto.RequestParam;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Author Jong
 * @Date 2020/12/27 12:03
 * @Version 1.0
 */
public class RpcRequest {

    /**
     * provider address
     */
    private String hostAddress;
    /**
     * listen port
     */
    private int port;

    public RpcRequest(String hostAddress, int port) {
        this.hostAddress = hostAddress;
        this.port = port;
    }

    /**
     * return a result which send rpc request
     */
    public Object sendRequest(RequestParam requestParam) throws IOException {
        try (
                Socket socket = newSocket();
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {
            out.writeObject(requestParam);
            out.flush();

            Object result = in.readObject();
            return result;
        } catch (Exception e) {
            System.err.printf("send a rpc request occurs a error: {%s}\n",e);
            e.printStackTrace();
        }
        return null;
    }

    private Socket newSocket() throws IOException {
        Socket socket = new Socket(hostAddress, port);
        return socket;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
