package com.as.test.webservice.soap;

import java.io.IOException;
import java.net.*;

/**
 * desc:
 * author: as
 * date: 2020/1/12
 */
public class SoapProtocolClient {
    public static void main(String[] args) {
//        try {
//            Socket socket = new Socket("localhost", 9080);
//            socket.
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            ServerSocket serverSocket = new ServerSocket();
            SocketAddress socketAddress = new InetSocketAddress(1234);
            serverSocket.bind(socketAddress);
            serverSocket.accept();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
