package com.as.test.io;

import com.alibaba.fastjson.JSONObject;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipNativeInitializationException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
pom 引用情况
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-compress</artifactId>
    <version>1.25.0</version> <!-- 使用你需要的版本 -->
</dependency>
<dependency>
    <groupId>net.sf.sevenzipjbinding</groupId>
    <artifactId>sevenzipjbinding</artifactId>
    <version>16.02-2.01</version>
</dependency>
<dependency>
    <groupId>net.sf.sevenzipjbinding</groupId>
    <artifactId>sevenzipjbinding-all-platforms</artifactId>
    <version>16.02-2.01</version>
</dependency>
 */

public class UncompressUtils {

    public enum CompressType {
        ZIP,
        SEVENZ,
        TAR
    }


    /**
     * 根据上传的文件名获取压缩类型
     *
     * @param fileName 上传文件名
     * @return 压缩类型
     */
    public static CompressType compressTypeExtract(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            throw new RuntimeException("文件名为空");
        }

        if (fileName.endsWith("zip")) {
            return CompressType.ZIP;
        } else if (fileName.endsWith("7z")) {
            return CompressType.SEVENZ;
        } else if (fileName.endsWith("tar")) {
            return CompressType.TAR;
        } else {
            throw new RuntimeException("暂不支持该压缩类型");
        }
    }

    /**
     * 仅提取第一层文件
     *
     * @param fileName 压缩文件名称
     * @param is       压缩文件流
     * @return kv
     */
    public static Map<String, File> extractFiles(String fileName, InputStream is) {
        return extractFiles(compressTypeExtract(fileName), is);
    }

    /**
     * 仅提取第一层文件
     *
     * @param type 类型
     * @param is   压缩文件流
     * @return kv
     */
    public static Map<String, File> extractFiles(CompressType type, InputStream is) {
        if (null == type) {
            throw new RuntimeException("压缩类型为空");
        }

        Map<String, File> rs = new HashMap<>();
        try {
            switch (type) {
                case ZIP:
                    rs = extractFilesZIP(is);
                    break;
                case SEVENZ:
                    rs = extractFiles7z(is);
                    break;
                case TAR:
                    rs = extractFilesTar(is);
                    break;
                default:
                    ;
            }
        } catch (Exception e) {
            throw new RuntimeException("文件提取异常", e);
        }

        return rs;
    }

    /**
     * 删除临时文件
     *
     * @param files
     */
    public static void delTempFiles(Map<String, File> files) {
        if (null == files) {
            return;
        }

        files.forEach((s, file) -> {
            if (file.exists()) {
                file.delete();
            }
        });
    }

    private static Map<String, File> extractFilesZIP(InputStream is) throws IOException {
        Map<String, File> rs = new HashMap<>();
        ZipInputStream zis = new ZipInputStream(is);
        ZipEntry ze;
        while (null != (ze = zis.getNextEntry())) {
            if (ze.isDirectory()) {
                continue;
            }
            String name = ze.getName();
            String[] split = name.split("\\.");
            File tempFile = File.createTempFile("temp" + split[0], "." + split[1]);
            OutputStream os = Files.newOutputStream(tempFile.toPath());
            write(zis, os);
            zis.closeEntry();

            rs.put(name, tempFile);
        }
        return rs;
    }

    private static Map<String, File> extractFilesTar(InputStream is) throws IOException {
        Map<String, File> rs = new HashMap<>();
        TarArchiveInputStream tis = new TarArchiveInputStream(is);
        TarArchiveEntry ze;
        while (null != (ze = tis.getNextEntry())) {
            if (ze.isDirectory()) {
                continue;
            }
            String name = ze.getName();
            String[] split = name.split("\\.");
            File tempFile = File.createTempFile("temp" + split[0], "." + split[1]);
            OutputStream os = Files.newOutputStream(tempFile.toPath());
            write(tis, os);
            rs.put(name, tempFile);
        }
        return rs;
    }

    private static Map<String, File> extractFiles7z(InputStream is) throws IOException, SevenZipNativeInitializationException {
        Map<String, File> rs = new HashMap<>();
        SevenZip.initSevenZipFromPlatformJAR();

        File temp = File.createTempFile("temp", ".7z");
        write(is, Files.newOutputStream(temp.toPath()));

        RandomAccessFile randomAccessFile = new RandomAccessFile(temp, "r");
        IInArchive inArchive = SevenZip.openInArchive(null, new RandomAccessFileInStream(randomAccessFile));
        ISimpleInArchive simpleInArchive = inArchive.getSimpleInterface();
        ISimpleInArchiveItem[] archiveItems = simpleInArchive.getArchiveItems();
        for (ISimpleInArchiveItem archiveItem : archiveItems) {
            if (archiveItem.isFolder()) {
                continue;
            }

            String name = archiveItem.getPath();
            String[] split = name.split("\\.");
            File tempFile = File.createTempFile("temp" + split[0], "." + split[1]);
            FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
            archiveItem.extractSlow(data -> {
                int length = data.length;
                try {
                    fileOutputStream.write(data, 0, length);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return length;
            });
            fileOutputStream.flush();
            fileOutputStream.close();

            rs.put(name, tempFile);
        }

        if (temp.exists()) {
            temp.delete();
        }
        return rs;
    }

    private static void write(InputStream is, OutputStream os) {
        byte[] bs = new byte[1024];
        int l;
        try {
            while ((l = is.read(bs)) > 0) {
                os.write(bs, 0, l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\88452\\Desktop\\test.tar";
        String fileName = "test.tar";

        try {
            Map<String, File> stringFileMap = extractFiles(fileName, new FileInputStream(filePath));
            System.out.println(JSONObject.toJSONString(stringFileMap));
            delTempFiles(stringFileMap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
