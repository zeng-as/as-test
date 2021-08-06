package com.as.test.proxy;

public class Test {

    public static void main(String[] args) {
        StaticProxy sp = new StaticProxy();
        StaticProxy.PenProxy penProxy = sp.new PenProxy(sp.new GangBi());
        penProxy.write("hello world! ");
    }

}
