package com.as.test.poi;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Desc:
 * Create by scrawl on 2018/5/3
 */
public class PoiUtil {

    public static void readXls() {

    }


    public static void main(String[] args) throws IOException, InvalidFormatException {
        File file = new File("C:\\Users\\88452\\Desktop\\新建文件夹\\test.xlsx");
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
        for (int i=0; i<=rowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            if (null == row) {
                continue;
            }
            XSSFCell cell = row.getCell(1);
            if (null == cell) {
                continue;
            }
            cell.setCellValue("test"+i);
            XSSFHyperlink hyperlink = cell.getHyperlink();
            String s = hyperlink.getAddress().replaceAll("目录1/", "目录3/");
            XSSFHyperlink l = workbook.getCreationHelper().createHyperlink(HyperlinkType.FILE);
            l.setAddress(s);
            cell.setHyperlink(l);

//            String s = hyperlink.getAddress().replaceAll("目录1/", "目录2/");
//            hyperlink.setAddress(s);
//            cell.setHyperlink(hyperlink);
        }
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();
    }
}
