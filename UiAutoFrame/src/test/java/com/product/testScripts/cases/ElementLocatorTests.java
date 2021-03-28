package com.product.testScripts.cases;

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

public class ElementLocatorTests extends CaseBase {
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
        loginPage.password().sendKeys(password);
        loginPage.loginButton().click();
        Thread.sleep(2000);
        loginPage.defaultToFrame();
        Assert.assertTrue(driver.getPageSource().contains("未读邮件"));

        Thread.sleep(2000);
        driver.findElement(By.id("_mail_tabitem_1_119text")).click();
        driver.findElement(By.xpath("//div[contains(@id,'_mail_button_')]/span[contains(text(),'新建联系人')]")).click();
        driver.findElement(By.id("input_N")).sendKeys("123456");
        driver.findElement(By.xpath("//div[@id='iaddress_MAIL_wrap']/dl/dd/div/input")).sendKeys("132132");
        driver.findElement((By.id("fly0"))).click();
        driver.findElement(By.xpath("//div[@id='iaddress_TEL_wrap']/dl/dd/div/input")).sendKeys("15268459231");
        driver.findElement(By.id("input_DETAIL")).sendKeys("这是一个描述哟哟哟~~~");
        driver.findElement(By.xpath("//*[contains(@id,'_mail_button_')]/span[contains(.,'确 定')]")).click();

        Assert.assertTrue(driver.getPageSource().contains("请正确填写邮件地址。"));
        Thread.sleep(5000);
    }

    // 封装登录方法后的调用
    public void testMailLogin_Action() throws Exception {
        Login_Action.execute(driver,baseUrl,username,password);
        Thread.sleep(3000);
        Assert.assertTrue(driver.getPageSource().contains("未读邮件"));

    }
}
