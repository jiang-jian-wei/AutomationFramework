package com.product.pageObjects;

import com.product.util.ObjectMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebElement element = null;
    // 指定页面元素定位表达式配置文件的绝对路径
    private static final String ElementProFile = System.getProperty("user.dir")+"\\src\\test\\resources\\objectmaps\\126objectMap.properties";
    private ObjectMap objectMap = new ObjectMap(ElementProFile);

    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    // 获取登录后主页中的通讯录按钮的元素定位
    public WebElement addressLink() throws Exception {
        element = driver.findElement(objectMap.getLocator("126mail.homePage.addressBook"));
        return element;
    }

    // 如果要在HomePage页面操作更多的链接元素，可以根据需要自行定义


}
