package com.as.test.poi;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.poi.xslf.usermodel.XSLFRelation.OLE_OBJECT;

/**
 * pom信息
 *         <dependency>
 *             <groupId>org.apache.poi</groupId>
 *             <artifactId>poi</artifactId>
 *             <version>5.2.3</version>
 *         </dependency>
 *         <dependency>
 *             <groupId>org.apache.poi</groupId>
 *             <artifactId>poi-ooxml</artifactId>
 *             <version>5.2.3</version>
 *         </dependency>
 *         <dependency>
 *             <groupId>org.apache.poi</groupId>
 *             <artifactId>poi-scratchpad</artifactId>
 *             <version>5.2.3</version>
 *         </dependency>
 */
public class PoiUtil {

    @Data
    public static class EmbeddedFile {
        @JSONField(serialize = false)
        private byte[] data;
        private String fileName;
        private String embedName;
        private int sheet;
        private int col1;
        private int col2;
        private int row1;
        private int row2;
    }

    public static Map<String, EmbeddedFile> readEmbeddedFiles(InputStream is) {
        Map<String, EmbeddedFile> rs = new HashMap<>();

        try (XSSFWorkbook sheets = new XSSFWorkbook(is)) {
            int size = sheets.getNumberOfSheets();

            for (int i = 0; i < size; i++) {
                XSSFSheet sheet = sheets.getSheetAt(i);
                Drawing<?> dp = sheet.getDrawingPatriarch();
                if (null == dp) {
                    continue;
                }

                for (Shape shape : dp) {
                    if (!(shape instanceof ObjectData)) {
                        continue;
                    }

                    ObjectData od = (ObjectData) shape;
                    EmbeddedFile embeddedFile = new EmbeddedFile();
                    rs.put(od.getFileName(), embeddedFile);

                    embeddedFile.setEmbedName(od.getFileName());
                    embeddedFile.setSheet(i);
                    ChildAnchor chAnc = shape.getAnchor();
                    if (chAnc instanceof ClientAnchor) {
                        ClientAnchor anc = (ClientAnchor) chAnc;
                        embeddedFile.setCol1(anc.getCol1());
                        embeddedFile.setCol2(anc.getCol2());
                        embeddedFile.setRow1(anc.getRow1());
                        embeddedFile.setRow2(anc.getRow2());
                    }
                }
            }

            List<PackagePart> parts = sheets.getAllEmbeddedParts();
            for (PackagePart embeddedPart : parts) {
                String name = embeddedPart.getPartName().getName();
                if (!rs.containsKey(name)) {
                    continue;
                }

                EmbeddedFile embeddedFile = rs.get(name);
                if (!OLE_OBJECT.getContentType().equals(embeddedPart.getContentType())) {
                    continue;
                }

                POIFSFileSystem filesystem = new POIFSFileSystem(embeddedPart.getInputStream());
                DirectoryNode rootNode = filesystem.getRoot();
                for (Entry entry : rootNode) {
                    if (!(entry instanceof DocumentEntry)) {
                        continue;
                    }

                    DocumentEntry documentEntry = (DocumentEntry) entry;
                    if (!documentEntry.getName().contains("Ole10Native")) {
                        continue;
                    }

                    Ole10Native ole10Native = Ole10Native.createFromEmbeddedOleObject(rootNode);
                    embeddedFile.setFileName(ole10Native.getLabel2());
                    embeddedFile.setData(ole10Native.getDataBuffer());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 输出日志
        }
        return rs;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String xlsName = "C:\\Users\\88452\\Desktop\\test.xlsx";
        Map<String, EmbeddedFile> map = readEmbeddedFiles(new FileInputStream(xlsName));

        String directPath = "C:\\Users\\88452\\Desktop\\temp\\";
        map.forEach((s, embeddedFile) -> {
            byte[] data = embeddedFile.getData();
            if (null != data && data.length > 0) {
                try (FileOutputStream fos = new FileOutputStream(directPath + embeddedFile.getFileName())) {
                    fos.write(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
