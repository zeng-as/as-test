package com.as.test;

/**
 * @author as
 * @desc
 * @since 2019/4/16 10:45
 */
public class HelloNative {

    public static native void sayHello();

    public static void main(String[] args) {
        sayHello();
    }
}
