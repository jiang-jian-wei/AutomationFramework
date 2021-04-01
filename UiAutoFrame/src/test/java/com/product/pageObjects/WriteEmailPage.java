package com.product.pageObjects;

import com.product.util.ObjectMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WriteEmailPage {
    private WebElement element = null;
    // 指定页面元素定位表达式配置文件的绝对路径
    private static final String ElementProFile = System.getProperty("user.dir")+"\\src\\test\\resources\\objectmaps\\126objectMap.properties";
    private ObjectMap objectMap = new ObjectMap(ElementProFile);

    private WebDriver driver;

    public WriteEmailPage(WebDriver driver){
        this.driver = driver;
    }

    // 收件人输入框
    public WebElement recipient() throws Exception {
        element = driver.findElement(objectMap.getLocator("126mail.writeEmail.emailRecipient"));
        return element;
    }

    // 邮件主题
    public WebElement subject() throws Exception {
        element = driver.findElement(objectMap.getLocator("126mail.writeEmail.emailSubject"));
        return element;
    }

    // 邮件附件
    public WebElement attachFile() throws Exception {
        element = driver.findElement(objectMap.getLocator("126mail.writeEmail.attachFile"));
        return element;
    }

    // 发送按钮
    public WebElement sendButton() throws Exception {
        element = driver.findElement(objectMap.getLocator("126mail.writeEmail.sendEmailButton"));
        return element;
    }

}
