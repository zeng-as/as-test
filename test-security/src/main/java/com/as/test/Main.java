package com.as.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Properties;

public class Main {

    public static void tt() {
        String fp = "E:\\a.txt";
        File file = new File(fp);
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
                String s = br.readLine();
                System.out.println(s);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return null;
        });
    }

    public static void main(String[] args) {
        Main.tt();
    }
}