package com.as.test.utils;

import com.alibaba.fastjson.JSONObject;
import com.as.test.pojo.Book;
import com.as.test.pojo.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc:
 * author: as
 * date: 2019/10/8
 */
public class JsonUtil {

    public static void main(String[] args) throws JsonProcessingException {
        Map<String, Object> m = new HashMap<>();
        List<Book> books = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        books2.add(new Book("语文", null));
        books.add(new Book("英语", books2));
        Student s = new Student("asd", 123, 18, "asdasd", "asd", books);
        m.put("student", s);
        System.out.println(JSONObject.toJSONString(m));

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(m));
    }
}
