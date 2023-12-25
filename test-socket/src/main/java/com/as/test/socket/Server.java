package com.as.test.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Arrays;

/**
 * Desc:
 * Create by scrawl on 2018/5/2
 */
public class Server {

    public static void main(String[] args) {
        int port = 55535;
        try (ServerSocket server = new ServerSocket(port, 1)) {
            while (true) {
                Socket accept = server.accept();
//                accept.setSoTimeout(3000);
                InputStream is = accept.getInputStream();
                byte[] bs = new byte[1024];
                int i;
                try {
                    while ((i = is.read(bs)) > 0) {
                        byte[] bytes = Arrays.copyOfRange(bs, 0, i);
                        String s = new String(bytes, "GBK");
                        System.out.println(s + "|");

                        byte[] bytes1 = s.getBytes();
                        System.out.println(new String(bytes1));
                    }
                    //                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    //                String s;
                    //                while (null != (s = (br.readLine()))) {
                    //                    for (byte aByte : s.getBytes()) {
                    //                        System.out.println(Integer.toHexString(aByte));
                    //                    }
                    //                    System.out.println(s);
                    //                }
                } catch (SocketTimeoutException e) {
                    e.printStackTrace();
                }

                OutputStream os = accept.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                bw.write("0");
                bw.flush();
                accept.shutdownOutput();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
