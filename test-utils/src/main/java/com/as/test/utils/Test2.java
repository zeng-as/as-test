package com.as.test.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author as.
 * @since 2021/3/11
 */
public class Test2 {

    public static boolean[] canEat(Integer[] candiesCount, int[][] queries) {
        int countSize = candiesCount.length;
        long[] tempCount = new long[countSize];
        long tc = 0;
        for (int i=0; i<countSize; i++) {
            tc = tc + candiesCount[i];
            tempCount[i] = tc;
        }

        int size = queries.length;
        boolean[] answer = new boolean[size];
        for (int i=0; i<size; i++) {
            int[] tmp = queries[i];
            // 喜欢的类型，对应candiesCount下标
            int favoriteType = tmp[0];
            // 喜欢的天数
            int favoriteDay = tmp[1];
            // 每日上限
            int dailyCap = tmp[2];
            boolean luckDay = false;

            // 获取糖果上限，以及喜欢糖果数量
            long maxSuger = tempCount[favoriteType];
            long maxWithout = maxSuger - candiesCount[favoriteType];

            // 最小需求
            long min = favoriteDay + 1;
            // 最大需求
            long max = Long.MAX_VALUE / min < dailyCap ? Long.MAX_VALUE : min * dailyCap;

            if (maxSuger >= min && maxWithout < max) {
                luckDay = true;
            }

            answer[i] = luckDay;
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
//        URL resource = ClassLoader.getSystemClassLoader().getResource("file1.txt");
//        BufferedReader br = new BufferedReader(new FileReader(new File(resource.getFile())));
//        String param1 = br.readLine();
//        br.close();
//        List<Integer> objs = JSONObject.parseArray(param1, Integer.class);
//        Integer[] candiesCount = new Integer[objs.size()];
//        candiesCount = objs.toArray(candiesCount);
//        int[][] queries = new int[][]{{98151,852175948,740371266}};
//        boolean[] booleans = canEat(candiesCount, queries);
//        System.out.println(Arrays.toString(booleans));
    }
}
