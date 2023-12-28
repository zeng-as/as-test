package com.as.test;

import java.io.Closeable;
import java.io.IOException;

public class Test {

    public static class Temp implements Closeable {

        @Override
        public void close() throws IOException {
            System.out.println("close lo");
        }
    }

    public static void main(String[] args) {
        try (Temp t = new Temp()) {
            int i = 1/0;
        } catch (Exception e) {
            System.out.println("异常啦");
        }
    }
}
