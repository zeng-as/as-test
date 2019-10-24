package com.as.test.utils;

import com.as.test.pojo.Book;
import com.as.test.pojo.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * desc:
 * author: as
 * date: 2019/9/29
 */
public class XmlUtil {
    public static void main(String[] args) throws JsonProcessingException, InterruptedException {
//        List<Book> books = Arrays.asList(new Book("英语书"), new Book("语文书"));
//        Student student = new Student("as", 12, books);
//        ObjectMapper mapper = new XmlMapper();
//        String s = mapper.writeValueAsString(student);
//        System.out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + s);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println("当前开关节点为4");

        System.out.println("新规则服务-开始时间 " + sdf.format(new Date()));
        Thread.sleep(267);
        System.out.println("新规则服务-结束时间 " + sdf.format(new Date()));

//        System.out.println("原规则服务-开始时间 " + sdf.format(new Date()));
//        Thread.sleep(489);
//        System.out.println("原规则服务-结束时间 " + sdf.format(new Date()));

    }
}
