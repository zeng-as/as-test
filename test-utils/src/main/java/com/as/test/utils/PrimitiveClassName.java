package com.as.test.utils;

import java.util.*;

/**
 * @author as
 * @date 2022/5/30
 * @desc
 */
public class PrimitiveClassName {
    public static void main(String[] args) {
        String property = System.getProperty("sun.boot.class.path");
        Properties properties = System.getProperties();
        Set<String> strings = properties.stringPropertyNames();
        for (String string : strings) {
            System.out.println("【" + string + "】:  " + properties.get(string));
        }
    }
}
