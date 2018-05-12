package com.as.test.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Desc:
 * Create by scrawl on 2018/5/11
 */
public class TransUtil {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Parent{
        private int age;
        private int sex;
    }

    @Data
    @AllArgsConstructor
    static class Child extends Parent{
        private String name;
    }

    public static void main(String[] args) {
        Parent parent = new Parent(18, 1);
        Child child = (Child) parent;
        System.out.println(child);
    }
}
