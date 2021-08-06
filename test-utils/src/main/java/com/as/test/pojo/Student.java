package com.as.test.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * desc:
 * author: as
 * date: 2019/9/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
    private int age;
    private Integer ageNew;
    private String idCard;
    private String planID;
    private List<Book> bookList;
}
