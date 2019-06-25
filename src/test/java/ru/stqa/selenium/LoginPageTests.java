package ru.stqa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.HomePageAuthHelper;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;


import java.awt.*;

public class LoginPageTests extends TestsBase {

    private HomePageHelper homePage;
    private LoginPageHelper loginPage;
    private HomePageAuthHelper homePageAuth;

    @BeforeMethod
    public void initTests()  {

        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        loginPage = PageFactory.initElements(driver,LoginPageHelper.class);
        homePageAuth = PageFactory.initElements(driver,HomePageAuthHelper.class);


    }

    @Test
    public void loginPositive() {

        homePage.waitUntilPageIsLoaded();
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.enterLoginPassword(LOGIN, PASSWORD);
        homePageAuth.waitUntilPageIsLoaded();
        Assert.assertTrue(homePageAuth.profileButtonTitleContainsText(LOGIN));
    }

    @Test
    public void loginNegative() throws AWTException{

        homePage.waitUntilPageIsLoaded().openLoginPage();
        loginPage.waitUntilPageIsLoaded().enterLoginPassword("jjjj","syuyb87677");
        if(!loginPage.wrongMessageIsDisplayed()) {
            loginPage.cancelPopUpWindow();
        }

        int counter = 0;
        if(loginPage.wrongMessageIsDisplayed())counter++;
        homePage.waitUntilPageIsLoaded();
        if(homePage.homeIconIsDisplayed())counter++;
        if(homePage.loginIconIsDisplayed())counter++;
        if(homePage.registrationIconIsDisplayed())counter++;

        Assert.assertEquals(counter,4);

    }

}
