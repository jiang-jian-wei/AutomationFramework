package com.product.appModules;

import com.product.pageObjects.HomePage;
import com.product.pageObjects.AddressBookPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AddContactPerson_Action {
    public static void execute(WebDriver driver,String baseUrl,String username,String password,String contactName,String
                               contactEmail,Boolean star,String contactMobile,String desc) throws Exception {
        Login_Action.execute(driver,baseUrl,username,password);
        Thread.sleep(3000);
        Assert.assertTrue(driver.getPageSource().contains("未读邮件"));

        HomePage homePage = new HomePage(driver);
        homePage.addressLink().click();
        Thread.sleep(2000);
        AddressBookPage addressBookPage = new AddressBookPage(driver);
        Thread.sleep(3000);
        addressBookPage.createContactPersonButton().click();
        addressBookPage.contactPersonName().sendKeys(contactName);
        addressBookPage.contactPersonEmail().sendKeys(contactEmail);
        if (star){
            addressBookPage.contactPersonStar().click();
        }
        addressBookPage.contactPersonPhone().sendKeys(contactMobile);
        addressBookPage.contactPersonDesc().sendKeys(desc);
        addressBookPage.contactPersonSaveButton().click();
        Thread.sleep(3000);
    }
}
