package com.product.testScripts;

import com.product.util.ObjectMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class myTest1 extends CaseBase{

    // 指定页面元素定位表达式配置文件的绝对路径
    private static final String ElementNameProFile = System.getProperty("user.dir")+"\\src\\test\\resources\\objectmaps\\126objectMap.properties";
    private ObjectMap objectMap = new ObjectMap(ElementNameProFile);

    @Value("${url.email}")
    String baseUrl;

    Properties properties;

    @Test
    public void test1() throws Exception {
        System.out.println(System.getProperty("user.dir")+"\\src\\test\\resources\\objectmaps\\126objectMap.properties");
        System.out.println(baseUrl);



        System.out.println(objectMap.getLocator("126mail.loginPage.iframe").toString());
    }

    @Test
    public void test2() throws InterruptedException {
        properties = new Properties();
        try {
            Reader in = new InputStreamReader(new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\objectmaps\\126objectMap.properties"),"utf-8");
            properties.load(in);
            in.close();
        } catch (IOException e) {
            Reporter.log("读取对象文件出错");
            e.printStackTrace();
        }

        String locator = properties.getProperty("126mail.loginPage.iframe");
        // 将配置文件中的定位类型存入locatorType变量，将定位表达式的值存入locatorValue变量
        String locatorType = locator.split(">")[0];
        String locatorValue = locator.split(">")[1];
        // 输出locatorType和locatorValue的值，验证是否赋值正确
        System.out.println("获取的定位类型："+locatorType+"\t获取的定位表达式："+locatorValue);

        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.switchTo().frame(driver.findElement(By.xpath(locatorValue)));

        driver.findElement(By.xpath("//input[contains(@placeholder,'邮箱帐号或手机号码')]")).sendKeys("121");
        driver.findElement(By.xpath("//*[contains(@data-placeholder,'输入密码')]")).sendKeys("15454");

        Thread.sleep(5000);
        driver.quit();
    }
}
