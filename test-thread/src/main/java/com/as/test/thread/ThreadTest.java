package com.as.test.thread;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * @author as
 * @desc
 * @since 2019/4/23 16:14
 */
public class ThreadTest {
    public static void main(String[] args) {
//        System.setProperty
//                ("java.util.concurrent.ForkJoinPool.common.parallelism", "50");
//        CompletableFuture.runAsync(() -> System.out.println("ggaga"));
        String a = "07";
        System.out.println(Integer.parseInt(a));
    }
}
