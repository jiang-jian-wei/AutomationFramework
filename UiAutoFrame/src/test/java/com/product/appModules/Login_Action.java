package com.product.appModules;

import com.product.pageObjects.LoginPage;
import org.openqa.selenium.WebDriver;

public class Login_Action {
    public static void execute(WebDriver driver,String baseUrl,String username,String password) throws Exception {
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.switchToFrame();
        loginPage.userName().sendKeys(username);
        loginPage.password().sendKeys(password);
        loginPage.loginButton().click();
        loginPage.defaultToFrame();
    }
}
