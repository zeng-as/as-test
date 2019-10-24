package com.as.test;

import java.util.*;

/**
 * desc:
 * author: as
 * date: 2019/10/16
 */
public class Arith {
    public static void main(String[] args) {
//        int[] ary = new int[10];
//        Random r = new Random(37);
//        for (int i=0;i<10;i++){
//            System.out.println(r.nextInt(10));
//        }
        List<String> a = new ArrayList<>();
        a.add("asdf");
        a.add("asd");
        a.add("asdf");
        a.add("asd1");
        a.add("asdf");
        a.add("asd1");
        a.add("asdf");
        a.add("asd1");
        a.add("asd1");
        a.add("asdf");
        a.add("asd");
        a.add("asd");
        a.add("asd1");
        a.add("asdf");
        a.add("asd");

        Set<String> b = new HashSet<>();
        for (int i=0; i<a.size(); i++) {
            String r = a.get(i);
            if (!b.contains(r)) {
               b.add(r);
            } else {
                a.remove(i);
                i--;
            }
        }

        System.out.println(a);
    }
}
