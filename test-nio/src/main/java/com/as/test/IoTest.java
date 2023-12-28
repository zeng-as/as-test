package com.as.test;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IoTest {

    public static void main(String[] args) {
        String fn = "C:\\Users\\88452\\Desktop\\test.txt";
        try (FileInputStream fis = new FileInputStream(fn)) {
            List<Byte> l = new ArrayList<>();

            byte[] bs = new byte[100];
            int s;
            while ((s = fis.read(bs)) != -1) {
                System.out.println(new String(Arrays.copyOf(bs, s)));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
