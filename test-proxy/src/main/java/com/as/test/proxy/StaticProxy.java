package com.as.test.proxy;

public class StaticProxy {

    interface Pen {
        void write(String text);
    }

    class GangBi implements Pen {

        @Override
        public void write(String text) {
            System.out.println("用钢笔写下：" + text);
        }
    }

    class QianBi implements Pen {
        @Override
        public void write(String text) {
            System.out.println("用铅笔写下：" + text);
        }
    }

    class PenProxy implements Pen {

        private Pen pen;

        public PenProxy(Pen pen) {
            this.pen = pen;
        }

        @Override
        public void write(String text) {
            this.pen.write(text);
        }
    }

    public static void main(String[] args) {
        StaticProxy sp = new StaticProxy();
        PenProxy penProxy = sp.new PenProxy(sp.new GangBi());
        penProxy.write("hello world! ");

        penProxy.pen = sp.new QianBi();
        penProxy.write("hello world!");
    }
}
