package ru.stqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;
import ru.stqa.selenium.util.DataProviders;

public class HomePageTests extends TestsBase {

    public HomePageHelper homePage;
    public LoginPageHelper loginPage;
    public EventNewWindowHelper eventNewWindow;

    @BeforeMethod
    public void initTests() {

        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        eventNewWindow = PageFactory.initElements(driver,EventNewWindowHelper.class);

        homePage.waitUntilPageIsLoaded();

    }

    @Test
    public void loginPageIsLoaded(){

        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        Assert.assertTrue(loginPage.itIsLoginPage());
    }

    @Test (dataProviderClass = DataProviders.class,dataProvider = "singleFilterByHoliday")
    public void singleFilterByHoliday(String holiday) {

        homePage.chooseFilterHoliday(holiday).waitEventsListReloaded();
        Assert.assertTrue(homePage.isEventsHoliday(holiday));
    }

    @Test (dataProviderClass = DataProviders.class,dataProvider = "singleFilterByConfession")
    public void singleFilterByConfession(String  confession) {

        homePage.chooseFilterConfession(confession).waitEventsListReloaded();
        Assert.assertTrue(homePage.isEventsConfession(confession));
    }

    @Test (dataProviderClass = DataProviders.class,dataProvider = "doubleFilterByHolidayAndConfession")
    public void doubleFilterHolidayAndConfession(String holiday, String confession){

        homePage.chooseFilterHoliday(holiday)
                .waitEventsListReloaded()
                .chooseFilterConfession(confession)
                .waitEventsListReloaded().sleep(3000);

        Assert.assertTrue(homePage.isEventsHolidayAndConfession(holiday, confession));
    }





    @Test (dataProviderClass = DataProviders.class,dataProvider = "singleFilterByHolidayWithWindow")
    public  void singleFilterByHolidayWithWindow(String holiday){

        homePage.chooseFilterHoliday(holiday).waitEventsListReloaded();
        homePage.areAllEventsByFilterHoliday(holiday);
    }




}
