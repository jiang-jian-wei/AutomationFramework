package com.product.pageObjects;

import com.product.util.ObjectMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddressBookPage {
    WebElement element = null;
    // 指定页面元素定位表达式配置文件的绝对路径
    private static final String ElementProFile = System.getProperty("user.dir")+"\\src\\test\\resources\\objectmaps\\126objectMap.properties";
    private ObjectMap objectMap = new ObjectMap(ElementProFile);


    private WebDriver driver;

    public AddressBookPage(WebDriver driver){
        this.driver = driver;
    }

    // 获取 新建联系人 按钮
    public WebElement createContactPersonButton() throws Exception {
        element = driver.findElement(objectMap.getLocator("126mail.addressBook.createContactPerson"));
        return element;
    }

    // 获取新建联系人界面中的姓名输入框
    public WebElement contactPersonName() throws Exception {
        element = driver.findElement(objectMap.getLocator("126mail.addressBook.contactPersonName"));
        return element;
    }

    // 获取新建联系人界面中的电子邮件输入框
    public WebElement contactPersonEmail() throws Exception {
        element = driver.findElement(objectMap.getLocator("126mail.addressBook.contactPersonEmail"));
        return element;
    }

    // 获取新建联系人界面中的星标联系人复选框
    public WebElement contactPersonStar() throws Exception {
        element = driver.findElement(objectMap.getLocator("126mail.addressBook.contactPersonStar"));
        return element;
    }

    // 获取新建联系人界面中的手机号输入框
    public WebElement contactPersonPhone() throws Exception {
        element = driver.findElement(objectMap.getLocator("126mail.addressBook.contactPersonPhone"));
        return element;
    }

    // 获取新建联系人界面中的描述输入框
    public WebElement contactPersonDesc() throws Exception {
        element = driver.findElement(objectMap.getLocator("126mail.addressBook.contactPersonDesc"));
        return element;
    }

    // 获取新建联系人界面中的确定按钮
    public WebElement contactPersonSaveButton() throws Exception {
        element = driver.findElement(objectMap.getLocator("126mail.addressBook.saveButton"));
        return element;
    }
}
