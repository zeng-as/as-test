package com.as.test.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Desc:
 * Create by scrawl on 2018/5/2
 */
public class Client {
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 55535;
        Socket socket = new Socket(host, port);
        OutputStream os = socket.getOutputStream();
        os.write("i am client!\n".getBytes("UTF-8"));
        socket.shutdownOutput();

        InputStream is = socket.getInputStream();
        System.out.println(is.getClass());
        is.read();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while((len = is.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, len, "UTF-8"));
        }
        System.out.println(sb);

        is.close();
        os.flush();
        os.close();
        socket.close();
    }
}
