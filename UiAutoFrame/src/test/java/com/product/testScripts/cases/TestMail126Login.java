package com.product.testScripts.cases;

import com.product.appModules.AddContactPerson_Action;
import com.product.appModules.Login_Action;
import com.product.pageObjects.LoginPage;
import com.product.testScripts.CaseBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestMail126Login extends CaseBase {
    public WebDriver driver;

    @Value("${url.email}")
    private String baseUrl;

    @Value("${126mail.username}")
    private String username;

    @Value("${126mail.password}")
    private String password;

    @BeforeMethod
    public void beforeMethod(){
        // 加载chrome浏览器驱动程序
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
        // 打开Chrome浏览器
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

    // 未封装登录方法前的测试步骤
    @Test
    public void testMailLogin() throws Exception {
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.switchToFrame();
        loginPage.userName().sendKeys(username);
        Thread.sleep(2000);
        loginPage.password().sendKeys(password);
        loginPage.loginButton().click();
        Thread.sleep(2000);
        loginPage.defaultToFrame();

        Assert.assertTrue(driver.getPageSource().contains("未读邮件"));
    }

    // 封装登录方法后的调用
    @Test
    public void testMailLogin_Action() throws Exception {
        Login_Action.execute(driver,baseUrl,username,password);
        Thread.sleep(3000);
        Assert.assertTrue(driver.getPageSource().contains("未读邮件"));

    }

    // 封装了新建联系人的调用
    @Test
    public void testCreateContactPerson() throws Exception {
        AddContactPerson_Action.execute(driver,baseUrl,username,password,"张三","zhangsan@126.com"
                ,true,"12345678901","这是一个描述");
        Thread.sleep(3000);
        Assert.assertTrue(driver.getPageSource().contains("张三"));
    }
}
