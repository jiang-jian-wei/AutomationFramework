package com.product.testScripts.cases;

import com.product.appModules.Login_Action;
import com.product.pageObjects.HomePage;
import com.product.pageObjects.WriteEmailPage;
import com.product.testScripts.CaseBase;
import com.product.util.KeyBoardUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TestSendEmail extends CaseBase {
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
        System.setProperty("java.awt.headless", "false");
        // 打开Chrome浏览器
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

    /**
     * 富文本操作方法1： 使用键盘tab键和 复制ctrl+v
     * 使用sendKey写入附件》》input框
     * @throws Exception
     */
    @Test
    public void testMailLogin_Action() throws Exception {
        Login_Action.execute(driver,baseUrl,username,password);
        Thread.sleep(3000);
        Assert.assertTrue(driver.getPageSource().contains("未读邮件"));

        HomePage homePage = new HomePage(driver);
        homePage.writeEmail().click();
        Thread.sleep(2000);
        WriteEmailPage writeEmailPage = new WriteEmailPage(driver);
        Thread.sleep(2000);
        writeEmailPage.recipient().sendKeys("15268459238@163.com");
        Thread.sleep(2000);
        writeEmailPage.subject().sendKeys("测试邮件");
        Thread.sleep(2000);
        KeyBoardUtils.pressTabKey();
        Thread.sleep(2000);
        KeyBoardUtils.setAndCtrlVClipboardData("这是一段测试邮件文本内容！！");
        writeEmailPage.attachFile().sendKeys("D:\\abc.png");
        Thread.sleep(2000);
        writeEmailPage.sendButton().click();
        Thread.sleep(2000);
    }

    /**
     * 富文本操作方法2： 使用JavaScript写入富文本
     * @throws Exception
     */
}
