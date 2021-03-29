package com.product.testScripts;

import com.product.util.TessUtil;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class GetCodeTest extends CaseBase{
    @Value("${url.baidu}")
    private String urlBaidu;

    ChromeDriver chromeDriver;

    WebDriverWait webDriverWait;

    @BeforeMethod
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
        chromeDriver = new ChromeDriver();
        webDriverWait = new WebDriverWait(chromeDriver,5);
        chromeDriver.get(urlBaidu);
    }

    @AfterMethod
    public void testAfter() throws InterruptedException {
        Thread.sleep(2000);
        chromeDriver.quit();
    }

    @Test
    public void getCodeTestBaiDu() throws IOException, TesseractException {

        chromeDriver.manage().window().maximize();
        byte[] bytes = TessUtil.takeScreenshot(chromeDriver);

        // 获取截图到本地
        FileOutputStream fos = new FileOutputStream("D:\\baidu.png");
        fos.write(bytes);
        fos.close();

        // 截取截图上需要验证码的部分
        WebElement codeImageElement = chromeDriver.findElement(By.id("lg"));
        BufferedImage codeImage = TessUtil.createElementImage(chromeDriver,codeImageElement,848,130,96,100);

        // 获取验证码
        String code = TessUtil.getVerificationCode("D:\\baiduCode.png",codeImage);
        System.out.println(code);
        Assert.assertEquals(code,"Bai","获取到的logo中的字段"+code);
    }

}
