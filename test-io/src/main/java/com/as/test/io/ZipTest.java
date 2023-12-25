package com.as.test.io;

import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipTest {

    public static void main(String[] args) {

        String f = "C:\\Users\\88452\\Desktop\\test.zip";

        try {
            if (f.endsWith(".zip")) {
                ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(f));
                String dest = "C:\\Users\\88452\\Desktop\\zip\\";
                ZipEntry ze = null;
                while (null != (ze = zipInputStream.getNextEntry())) {
                    String name = ze.getName();
                    System.out.println(name);
                    FileOutputStream fileOutputStream = new FileOutputStream(dest + name);
                    byte[] bs = new byte[1024];
                    int l;
                    while ((l = zipInputStream.read(bs)) > 0) {
                        fileOutputStream.write(bs, 0, l);
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    zipInputStream.closeEntry();
                }
            } else if (f.endsWith(".tar")) {
                TarArchiveInputStream tarArchiveInputStream = new TarArchiveInputStream(new FileInputStream(f));
                TarArchiveEntry ze = null;
                String dest = "C:\\Users\\88452\\Desktop\\tar\\";
                while (null != (ze = tarArchiveInputStream.getNextEntry())) {
                    String name = ze.getName();
                    System.out.println(name);
                    FileOutputStream fileOutputStream = new FileOutputStream(dest + name);
                    byte[] bs = new byte[1024];
                    int l;
                    while ((l = tarArchiveInputStream.read(bs)) > 0) {
                        fileOutputStream.write(bs, 0, l);
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            } else if (f.endsWith(".7z")) {
                String dest = "C:\\Users\\88452\\Desktop\\7z\\";
                SevenZip.initSevenZipFromPlatformJAR();
                RandomAccessFile randomAccessFile = new RandomAccessFile(new File(f), "r");
                IInArchive inArchive = SevenZip.openInArchive(null, new RandomAccessFileInStream(randomAccessFile));
                ISimpleInArchive simpleInArchive = inArchive.getSimpleInterface();
                ISimpleInArchiveItem[] archiveItems = simpleInArchive.getArchiveItems();
                for (ISimpleInArchiveItem archiveItem : archiveItems) {
                    String path = archiveItem.getPath();
                    System.out.println(path);
                    FileOutputStream fileOutputStream = new FileOutputStream(dest + path);
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
                }
            } else {
                System.out.println("..");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
