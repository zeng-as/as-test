package com.as.test.file;

import java.io.File;
import java.util.Arrays;

/**
 * @author as
 * @desc
 * @date 2018/12/23
 */
public class FileUtils {
    public static void main(String[] args) {
        String path = "C:\\Users\\as\\Pictures\\upload\\qrcode";
        File file = new File(path);
        File[] fs = file.listFiles();
        Arrays.stream(fs).forEach(f -> {
            String[] s = f.list();
            System.out.println(s[0].split("\\.")[0]);
        });
    }
}
