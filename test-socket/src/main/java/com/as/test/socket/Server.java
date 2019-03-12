package com.as.test.socket;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Desc:
 * Create by scrawl on 2018/5/2
 */
public class Server {

    public static void main(String[] args) throws IOException {
        int port = 55535;
        ServerSocket server = new ServerSocket(port);
        System.out.println("socket服务器开启，ip,port");
        Socket socket = server.accept();
        InputStream is = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while((len = is.read(bytes)) != -1) {
            String a =  new String(bytes, 0, len, "UTF-8");
            sb.append(a);
        }
        System.out.println(sb);

        OutputStream os = socket.getOutputStream();
        os.write("i am server!".getBytes("UTF-8"));

        os.flush();
        os.close();
        is.close();
        socket.close();
        server.close();
    }
}
