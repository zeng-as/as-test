package com.as.test.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


/**
 * @author as.
 * @since 2020/7/22
 */
@RestController
public class TestController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return "hello " + name + " !";
    }
}
