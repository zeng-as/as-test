package com.as.test.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Desc:
 * Create by scrawl on 2018/5/3
 */
public class io {
    public static void main(String[] args) throws Exception {
        String path = "E:\\idea-workspace\\bpm-workspace\\TP_manager\\mdb-core\\src\\main\\java";
        String path2 = "E:\\idea-workspace\\bpm-workspace\\TP_manager\\web\\src\\main\\java";
        String path3 = "E:\\idea-workspace\\bpm-workspace\\TP_manager\\web\\src\\main\\webapp\\WEB-INF\\views";
        // step1: 获取指定路径下面所有文件
        List<File> files = new ArrayList<>();
        File file = new File(path);
        getFiles(file, files);

        file = new File(path2);
        getFiles(file, files);

        file = new File(path3);
        getFiles(file, files);

        // step2：统计文件数量
        File target = new File("C:\\Users\\88452\\Desktop\\target2.txt");
        if (!target.exists()) {
            boolean rs = target.mkdir();
            if (!rs) {
                return ;
            }
        }

        OutputStream os = new FileOutputStream(target);
        files.forEach(f -> {
            try {
                readFileToFile(f, os);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        os.flush();
        os.close();
    }

    public static void getFiles(File file, List<File> files) {
        if (file.isFile()) {
            files.add(file);
        }

        File[] ls = file.listFiles();
        if (null == ls || ls.length == 0) {
            return;
        }
        Arrays.stream(ls).forEach(f -> getFiles(f, files));
    }

    public static void readFileToFile(File source, OutputStream os) throws Exception {
        byte[] b = new byte[1024];
        InputStream is = new FileInputStream(source);
        String fileName = source.getName();
        if (fileName.endsWith(".java") || fileName.endsWith(".html") || fileName.endsWith(".jsp")) {
            os.write(("文件名：" + source.getName() + "\t\n").getBytes());

            int i;
            while ((i = is.read(b)) > 0) {
                os.write(b, 0, i);
            }

            is.close();
        }
    }
}
