package org.zy.rpc.consumer.request;

import org.zy.rpc.service.model.RequestParam;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author jogin
 * @date 2020.8.15
 *
 * RPC Request
 */
public class RPCRequest {

    private String hostAddress;
    private int port;

    public RPCRequest(String hostAddress, int port) {
        this.hostAddress = hostAddress;
        this.port = port;
    }

    public Object sendRequest(RequestParam requestParam) throws IOException {
        try (
            Socket socket = newSocket();
            ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in=new ObjectInputStream(socket.getInputStream())
        ) {
            out.writeObject(requestParam);
            out.flush();

            Object result = in.readObject();
            return result;
        }catch (ClassNotFoundException e){
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
