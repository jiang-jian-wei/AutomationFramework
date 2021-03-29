package com.product.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    // 从excel文件获取数据的静态方法
    public static Object[][] getTestData(String excelFilePath,String sheetName) throws IOException {
        // 参数为传入的数据文件的绝对路径
        // 申明一个File对象
        File file = new File(excelFilePath);

        // 创建FileInputStream对象用于读取Excel文件
        FileInputStream inputStream = new FileInputStream(file);

        // 申明Workbook对象
        Workbook workbook = null;

        // 获取文件的扩展名，判断是“.xlsx“文件还是”.xls“文件
        String fileExtensionName = excelFilePath.substring(excelFilePath.indexOf("."));

        // 判断文件类型，如果是“.xlsx“则使用XSSFWorkbook 对象进行实例化
        // 判断文件类型，如果是“.xls“则使用HSSFWorkbook 对象进行实例化
        if (fileExtensionName.equals(".xlsx")){
            workbook = new XSSFWorkbook(inputStream);
        }else if (fileExtensionName.equals(".xls")){
            workbook = new HSSFWorkbook(inputStream);
        }

        // 通过sheetName参数生成Sheet对象
        Sheet sheet = workbook.getSheet(sheetName);

        // 获取Excel数据文件sheet1中数据的行数，getLastRowNum方法获取数据的最后行号
        // getFirstRowNum 方法获取数据的第一行行号，相减之后算出数据的行数
        // 注意：excel文件的行号和列号都是从0开始的
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

        // 创建名为“records”的List对象来存储从Excel数据文件读取的数据
        List<Object> records = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Cell cell;
        // 使用两个for循环遍历Excel文件的所有数据（除了第一行，第一行是数据列名称），所以i从1开始，而不是0开始
        for (int i = 1;i<rowCount + 1;i++){
            // 使用getRow方法获取行对象
            Row row = sheet.getRow(i);
            // 申明一个数组，用来存储Excel数据文件每行中的数据，数据的大小用getLastCellNum方法来进行动态申明，实现测试数据个数和数组大小一致
            String fields[] = new String[row.getLastCellNum()];
            for (int j = 0; j<row.getLastCellNum();j++){
                cell = row.getCell(j);
                if (null != cell){
                    //如果是数字类型
                    if (0 == cell.getCellType()){
                        //判断是不是日期格式
                        if(HSSFDateUtil.isCellDateFormatted(cell)){
                            fields[j] = simpleDateFormat.format(cell.getDateCellValue());
                        }
                    }
                    // 如果是字符串类型
                    if (1 == cell.getCellType()){
                        row.getCell(j).setCellType(XSSFCell.CELL_TYPE_STRING);
                        fields[j] = row.getCell(j).getStringCellValue();
                    }
                }
            }
            // 将fields的数据对象存储到records的List中
            records.add(fields);
        }
        // 定义函数的返回值，即Object[][]
        // 将存储测试数据的List转换为一个Object的二维数组
        Object[][] results = new Object[records.size()][];
        // 设置二维数组每行的值，每行是一个Object对象
        for (int i = 0;i<records.size();i++){
            results[i] = (Object[]) records.get(i);
        }
        return results;
    }
}
