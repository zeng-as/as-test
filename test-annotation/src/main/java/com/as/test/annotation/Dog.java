package com.as.test.annotation;

import java.lang.annotation.Annotation;

/**
 * desc:
 * author: as
 * date: 2019/7/29
 */
@Animal(lag = 4)
public class Dog {

    public static void main(String[] args) {
        Annotation[]  as = Dog.class.getAnnotations();
        System.out.println(as.length);
    }
}
