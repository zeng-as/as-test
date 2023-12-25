package com.as.test;

import redis.clients.jedis.JedisPooled;

public class Main {
    public static void main(String[] args) {
        JedisPooled jedisPooled = new JedisPooled("127.0.0.1", 6379);
        String test = jedisPooled.get("test");
        System.out.println(test);


    }
}