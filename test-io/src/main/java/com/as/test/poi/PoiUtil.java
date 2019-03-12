package com.as.test.poi;

import java.io.IOException;

/**
 * Desc:
 * Create by scrawl on 2018/5/3
 */
public class PoiUtil {


    public static void main(String[] args) throws IOException {
//        File file = new File("D:\\C盘搬家\\Desktop\\channel.xlsx");
//        File sqlFile = new File("D:\\C盘搬家\\Desktop\\channel.sql");
//        if (!sqlFile.exists()) {
//            sqlFile.createNewFile();
//        }
//        FileWriter fw = new FileWriter(sqlFile);
//
//        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
//        XSSFSheet sheet = workbook.getSheetAt(0);
//        Iterator<Row> rowIt = sheet.rowIterator();
//        while (rowIt.hasNext()) {
//            Row row = rowIt.next();
//            Cell cell = row.getCell(4);
//            String cellValue = cell.getStringCellValue();
//            if (StringUtils.isBlank(cellValue) || !cellValue.startsWith("http")) {
//                continue;
//            }
//
//            String idStr = cellValue.substring(cellValue.indexOf("linkCode=") + 9);
//            Long id = Long.valueOf(idStr);
//
//            String sql = "INSERT INTO tab_channellink(id, channelId, linkAddress, drainageTargetIds, drainageTarget, promotionProductIds, promotionProduct, remark, STATUS, TYPE, registerBanner, linkType, bgImg) VALUES\n" +
//                    "("+ id +", 37, 'https://www.niiwoo.com/html5/project/speed-loan/index.html#/register', '1,2', '一般用户,借款人', '1', '极速借', '百度简版', 1, 1, 'https://image.niiwoo.com/activity-manage/20180428/04db7385-1bf0-4347-bd07-f7732921be80.png', 0, 'https://image.niiwoo.com/activity-manage/20180428/0d82b8bf-3373-4991-bc0f-aecbefa11bd6.jpg');\n";
//            fw.write(sql);
//        }
//
//        fw.close();
//        fw.close();
    }
}
