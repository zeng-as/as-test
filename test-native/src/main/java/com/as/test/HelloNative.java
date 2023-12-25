package com.as.test;

import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author as
 * @desc
 * @since 2019/4/16 10:45
 */

public class HelloNative {
    public interface IntfA {

    }

    public interface IntfB extends IntfA {

    }

    public  class ClassA {
    }

    public static void test(List<String> a) {

    }
    public static void main(String[] args) {
        test(Collections.emptyList());
    }
}
