package com.as.test.thread;

/**
 * @author as.
 * @since 2020/8/25
 */
public class RunThread extends Thread {

    private volatile boolean isRunning = true;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void run() {
        System.out.println("进入到run方法中了");
        while (isRunning) {
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(isRunning);
        }
        System.out.println("线程执行完毕");
    }

    public static void main(String[] args) throws InterruptedException {
        RunThread thread = new RunThread();
        thread.start();
        Thread.sleep(1000);
        thread.setRunning(false);

    }
}
