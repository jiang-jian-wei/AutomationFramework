package com.product.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Author jjw
 * Date 2021/03/14
 */
public class CsvUtil {

    // 读取csv的静态方法,csv文件的相对路径作为函数参数
    public static Object[][] getTestData(String fileName) throws IOException {
        List<Object[]> records = new ArrayList<>();
        String record;

        // 设定utf-8字符集使用带缓冲的区的的字符输入流BufferedReader读取文件内容
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"utf-8"));

        // 忽略读取csv文件的标题行（第一行）
        file.readLine();

        /**
         * 遍历读取文件中除第一行外的其他所有行内容并存储在名为“records”的ArrayList中
         * 每一个records中存储的对象为一个String数组
         */
        while ((record = file.readLine()) != null){
            String fields[] = record.split(",");
            records.add(fields);
        }

        // 关闭文件对象
        file.close();

        // 定义函数返回值，即Object[][]
        // 将存储测试数据的List转换为一个Object的二维数组
        Object[][] results = new Object[records.size()][];

        // 设置二维数组每行的值，每行是一个Object对象
        for (int i = 0;i<records.size();i++){
            results[i] = records.get(i);
        }
        return results;
    }

    // 读取csv的静态方法,csv文件的相对路径作为函数参数
    public static List<String> getTestDataList(String fileName) throws IOException {
        List<String> records = new ArrayList<>();
        String record;

        // 设定utf-8字符集使用带缓冲的区的的字符输入流BufferedReader读取文件内容
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"utf-8"));
        // 忽略读取csv文件的标题行（第一行）,这里读取了第一行但是没存入列表里
        file.readLine();

        /**
         * 遍历读取文件中除第一行外的其他所有行内容并存储在名为“records”的ArrayList中
         * 每一个records中存储的对象为一个String数组
         */
        while ((record = file.readLine()) != null){
            records.add(record);
        }
        // 关闭文件对象
        file.close();

        return records;
    }
}
