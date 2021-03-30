package com.product.testScripts;

import com.product.testScripts.CaseBase;
import com.product.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

@Slf4j
public class ExcelTest extends CaseBase {

    @Test(dataProvider = "excel")
    public void test(String name,String email,String desc,String phone){
        System.out.println("name = " + name + ", email = " + email + ", phone = " + phone + ", desc = " + desc);
        log.info("获取数据成功");
    }

    @DataProvider(name = "excel")
    public Object[][] getTestData() throws IOException {
        Object[][] objects = ExcelUtil.getTestData(System.getProperty("user.dir")+"\\src\\test\\resources\\excelData\\126邮箱测试数据.xlsx","新建联系人测试数据");
        return objects;
    }
}
