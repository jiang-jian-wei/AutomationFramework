package com.product.pageObjects;

import com.product.util.ObjectMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebElement element = null;
    // 指定页面元素定位表达式配置文件的绝对路径
    private static final String ElementProFile = System.getProperty("user.dir")+"\\src\\test\\resources\\objectmaps\\126objectMap.properties";
    private ObjectMap objectMap = new ObjectMap(ElementProFile);

    private WebDriver driver;
    // 构造函数
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    // 进入iframe
    public void switchToFrame() throws Exception {
        Thread.sleep(2000);
        element = driver.findElement(objectMap.getLocator("126mail.loginPage.iframe"));
        driver.switchTo().frame(element);
    }

    // 退出iframe
    public void defaultToFrame(){
        driver.switchTo().defaultContent();
    }

    // 返回登录页面中的用户名输入框元素对象
    public WebElement userName() throws Exception {
        // 使用ObjectMap中的getLocator方法获取配置文件中关于用户名的定位方式和定位表达式
        element = driver.findElement(objectMap.getLocator("126mail.loginPage.username"));
        return element;
    }

    // 返回登录页面中的密码名输入框元素对象
    public WebElement password() throws Exception {
        // 使用ObjectMap中的getLocator方法获取配置文件中关于密码的定位方式和定位表达式
        element = driver.findElement(objectMap.getLocator("126mail.loginPage.password"));
        return element;
    }

    // 返回登录页面中的密码名登录按钮元素对象
    public WebElement loginButton() throws Exception {
        // 使用ObjectMap中的getLocator方法获取配置文件中关于密码的定位方式和定位表达式
        element = driver.findElement(objectMap.getLocator("126mail.loginPage.loginButton"));
        return element;
    }
}
