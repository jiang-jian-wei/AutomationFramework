package com.product.testScripts.cases;

import com.product.appModules.Login_Action;
import com.product.pageObjects.HomePage;
import com.product.pageObjects.WriteEmailPage;
import com.product.testScripts.CaseBase;
import com.product.util.KeyBoardUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FileUploadTest extends CaseBase {
    public WebDriver driver;

    @Value("${url.email}")
    private String baseUrl;

    @Value("${126mail.username}")
    private String username;

    @Value("${126mail.password}")
    private String password;

    @BeforeMethod
    public void beforeMethod(){
        logger.info("启动浏览器............");
        // 加载chrome浏览器驱动程序
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
        System.setProperty("java.awt.headless", "false");
        // 打开Chrome浏览器
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
        logger.info("Close Browser Success!");
    }

    // 借助第三方软件AutoIt上传文件
    @Test
    public void FileUploadTest() throws Exception {
        Login_Action.execute(driver,baseUrl,username,password);
        logger.info("登录成功............");
        Thread.sleep(3000);
        Assert.assertTrue(driver.getPageSource().contains("未读邮件"));

        HomePage homePage = new HomePage(driver);
        homePage.writeEmail().click();
        Thread.sleep(2000);
        WriteEmailPage writeEmailPage = new WriteEmailPage(driver);
        writeEmailPage.recipient().sendKeys("15268459238@163.com");
        writeEmailPage.subject().sendKeys("测试邮件");
        Thread.sleep(3000);
//        writeEmailPage.attachFile().sendKeys("D:\\abc.png");
//        driver.findElement(By.xpath("//a[contains(@id,'_attachAdd')]")).click();
    }
}
