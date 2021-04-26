package com.product.testScripts.cases;

import com.product.appModules.Login_Action;
import com.product.pageObjects.HomePage;
import com.product.pageObjects.WriteEmailPage;
import com.product.testScripts.CaseBase;
import com.product.util.KeyBoardUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
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

    /**
     * 富文本操作方法1： 使用键盘tab键和 复制ctrl+v
     * 使用sendKey写入附件》》input框
     * @throws Exception
     */
    @Test
    public void TestSendEmailByTab() throws Exception {
        Login_Action.execute(driver,baseUrl,username,password);
        logger.info("登录成功............");
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
    @Test
    public void TestSendEmailByScript() throws Exception {
        Login_Action.execute(driver,baseUrl,username,password);
        driver.manage().window().maximize();
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
        writeEmailPage.attachFile().sendKeys("D:\\abc.png");
        Thread.sleep(2000);
        driver.switchTo().frame(writeEmailPage.textFrame());
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
        javascriptExecutor.executeScript("document.getElementsByTagName('body')[0].innerHTML = '<div style=\"margin:0" +
                ";\">你好！</div><div style=\"margin:0;\">&nbsp; &nbsp; 这是一份测试邮件！</div><div style=\"margin:0;\"><img" +
                " src=\"https://mail.126.com/js6/s?func=mbox:getComposeData&amp;sid=SAPMGRZTWZjCwYfYVbTTsukEwhmFNrDg&amp" +
                ";composeId=c:1617622941295&amp;attachId=1&amp;rnd=0.8375253433375027\" orgwidth=\"378\" orgheight=\"382" +
                "\" data-image=\"1\" style=\"width: 378px; height: 382px; border: none;\"></div><div style=\"margin:0;\"" +
                ">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbs" +
                "p;<b>测试图片</b></div><div style=\"margin:0;\"><br></div>' ");
        Thread.sleep(3000);
        driver.switchTo().defaultContent();
        writeEmailPage.sendButton().click();
        Thread.sleep(2000);
    }

    /**
     * 富文本操作方法3： 找到组件按钮元素点击
     * @throws Exception
     */
}
