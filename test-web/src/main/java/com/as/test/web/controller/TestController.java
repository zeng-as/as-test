package com.as.test.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;


/**
 * @author as.
 * @since 2020/7/22
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return "hello " + name + " !";
    }
}
