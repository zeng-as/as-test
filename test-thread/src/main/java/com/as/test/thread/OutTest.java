package com.as.test.thread;

/**
 * @author as.
 * @since 2021/2/20
 */
public class OutTest {

    /**
     * @param digestArray
     * @param codeArray
     * 方法1：
     * 通过锁对象，实现交叉轮询
     */
    public static void function1(char[] digestArray, char[] codeArray) {
        Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                for (char c : digestArray) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ": " + c);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (lock) {
                for (char c : codeArray) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ": " + c);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }, "t2").start();
    }

    public static void function2(char[] digestArray, char[] codeArray) {

    }

    public static void main(String[] args) {
        char[] digestArray = "123456789".toCharArray();
        char[] codeArray = "ABCDEFGHI".toCharArray();

        function1(digestArray, codeArray);
    }
}
