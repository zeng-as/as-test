package com.as.test.file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author as
 * @desc
 * @date 2018/12/23
 */
public class FileUtils {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\88452\\Desktop\\test\\test2\\a.txt");
        if (!file.exists()) {
            boolean rs = file.mkdirs();
            System.out.println(rs);
//            file.createNewFile();
        }
    }
}
