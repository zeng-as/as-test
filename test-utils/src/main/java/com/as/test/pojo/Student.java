package com.as.test.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * desc:
 * author: as
 * date: 2019/9/29
 */
@Data
@AllArgsConstructor
public class Student {
    private String Name;
    private int Age;
    private String IDCard;
    private String PlanID;
    private List<Book> BookList;
}
