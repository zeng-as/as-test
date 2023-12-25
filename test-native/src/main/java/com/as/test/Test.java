package com.as.test;

import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Properties;

public class Test {

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


    @CallerSensitive
    public static void withCallerSensitive() {
        System.out.format("Method is called by %s%n", Reflection.getCallerClass());
    }

    /*无注解直接调用*/
    public static void noCallerSensitive() {
        System.out.format("Method is called by %s%n",Reflection.getCallerClass());
    }

    public static void main(String[] args) {
//        String property = System.getProperty("java.security.manager");
//        System.out.println(property);
//        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
//            Main.tt();
//            Test.tt();
//            return null;
//        });
        withCallerSensitive();
        noCallerSensitive();
    }
}
