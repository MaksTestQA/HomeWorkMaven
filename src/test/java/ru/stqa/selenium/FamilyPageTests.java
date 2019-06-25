package ru.stqa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.factory.WebDriverPool;
import ru.stqa.selenium.pages.*;

public class FamilyPageTests extends TestsBase{


    private HomePageHelper homePage;
    private LoginPageHelper loginPage;
    private HomePageAuthHelper homePageAuth;
    private ProfilePageHelper profilePage;
    private EditProfilePageHelper editProfilePage;
    private MyFamilyPageHelper myFamilyPage;

    @BeforeMethod
    public void initTests() throws InterruptedException {

        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        homePageAuth = PageFactory.initElements(driver,HomePageAuthHelper.class);
        profilePage = PageFactory.initElements(driver,ProfilePageHelper.class);
        editProfilePage = PageFactory.initElements(driver, EditProfilePageHelper.class);
        myFamilyPage = PageFactory.initElements(driver, MyFamilyPageHelper.class);






    }




    @Test
    public void profileFamilyInfoTest() throws InterruptedException {

        System.out.println("--------------------profileFamilyInfoTest is started--------------------");
        homePage.waitUntilPageIsLoaded().openLoginPage();
        loginPage.waitUntilPageIsLoaded().enterLoginPassword(LOGIN,PASSWORD);
        homePageAuth.waitUntilPageIsLoaded().goToProfile();
        profilePage.waitUntilPageIsLoaded();


        String confessionProfile = profilePage.confessionProfileData();
        String languageProfile = profilePage.languageProfileData();
        String foodsProfile = profilePage.foodsProfileData();
      //  System.out.println(confessionProfile);
        //System.out.println(languageProfile);
        //System.out.println(foodsProfile);


        if(!profilePage.checkProfileStatus("family")) {

            profilePage.goToEditProfilePage();
            editProfilePage.waitUntilPageIsLoaded().selectFamilyStatus().saveProfile();
        }
            profilePage.waitUntilPageIsLoaded();
            profilePage.goToHomePageAuth();
            homePageAuth.waitUntilPageIsLoaded().goToMyFamilyPage();
            myFamilyPage.waitUntilPageIsLoaded();


        String confessionMyFamily = myFamilyPage.confessionMyFamilyData();
        //System.out.println(confessionMyFamily);
        String languageMyFamily = myFamilyPage.languageMyFamilyData();
        //System.out.println(languageMyFamily);
        String foodsMyFamily = myFamilyPage.foodsMyFamilyData();
        //System.out.println(foodsMyFamily);

        int counter = 0;
        if(confessionProfile.equals(confessionMyFamily))  counter++;
        System.out.println("Confessions are the same - "+confessionProfile.equals(confessionMyFamily));

        if(languageProfile.equals(languageMyFamily))   counter++;
            System.out.println("Languages are the same - "+languageProfile.equals(languageMyFamily));

        if(foodsProfile.equals(foodsMyFamily))counter++;
            System.out.println("foods preferences are the same - "+foodsProfile.equals(foodsMyFamily));

        Assert.assertEquals(counter, 3);
        System.out.println("--------------------profileFamilyInfoTest is finished--------------------");
    }

}
