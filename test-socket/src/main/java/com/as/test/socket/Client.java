package com.as.test.socket;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Desc:
 * Create by scrawl on 2018/5/2
 */
public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        String host = "127.0.0.1";
        int port = 55535;
        Socket socket = new Socket(host, port);
        OutputStream os = socket.getOutputStream();
        String a = "水";
        byte[] bytes = a.getBytes();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(2000);
//            for (byte aByte : bytes) {
//                System.out.println(Integer.toHexString(aByte));
//            }
            os.write(a.getBytes());
        }

//        socket.shutdownOutput();

        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s;
        while (null != (s = (br.readLine()))) {
            System.out.println(s);
        }

        is.close();
        os.flush();
        os.close();
        socket.close();
    }
}
