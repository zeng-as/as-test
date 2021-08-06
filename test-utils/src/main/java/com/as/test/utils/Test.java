package com.as.test.utils;

import com.alibaba.fastjson.JSONObject;
import com.as.test.pojo.Student;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author as.
 * @since 2020/8/5
 */
public class Test {

    private Student st;

    private List<Student> students;

    public static void test(Student s) {

    }

    public static void main(String[] args) throws Exception {
        String tmp = "{\"name\": \"详细地址\",\"expression\": \"partyList{quoteRole:\\\"1\\\",partyType:\\\"1\\\"}.customerInfo.address\"}";
        Map map = JSONObject.parseObject(tmp, Map.class);
        System.out.println(map);
        String a = map.get("expression").toString();
        Pattern compile = Pattern.compile("(\\{.+)(})");
        Matcher matcher = compile.matcher(a);
        String s = "";
        while(matcher.find()) {
            s = matcher.group(0);
        }

        System.out.println(s);
        map = JSONObject.parseObject(s, Map.class);
        System.out.println(map);

//        Pattern compile2 = Pattern.compile("特定(?=类)");
//        Matcher matcher2 = compile2.matcher(a);
//        while(matcher2.find()) {
//            System.out.println(matcher2.group(0));
//        }
    }
}
