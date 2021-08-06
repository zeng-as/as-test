package com.as.test.annotation;

/**
 * desc:
 * author: as
 * date: 2019/12/2
 */
public class Test {

    public static void main(String[] args) throws ClassNotFoundException {
        Animal animal = Thread.currentThread().getContextClassLoader()
                .loadClass("com.as.test.annotation.Dog").getAnnotation(Animal.class);
        System.out.println(animal.lag());
        System.out.println(animal.name());
    }
}
