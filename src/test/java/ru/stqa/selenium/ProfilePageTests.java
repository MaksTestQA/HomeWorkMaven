package ru.stqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;

public class ProfilePageTests extends TestsBase {

    private HomePageHelper homePage;
    private LoginPageHelper loginPage;
    private HomePageAuthHelper homePageAuth;
    private ProfilePageHelper profilePage;
    private EditProfilePageHelper editProfilePage;

    @BeforeMethod
    public void initTest(){

    homePage = PageFactory.initElements(driver,HomePageHelper.class);
    loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
    homePageAuth = PageFactory.initElements(driver,HomePageAuthHelper.class);
    profilePage = PageFactory.initElements(driver,ProfilePageHelper.class);
    editProfilePage = PageFactory.initElements(driver, EditProfilePageHelper.class);

  }

    @Test
    public void profileTitleTest(){

      homePage.waitUntilPageIsLoaded().openLoginPage();
      loginPage.waitUntilPageIsLoaded().enterLoginPassword(LOGIN,PASSWORD);
      homePageAuth.waitUntilPageIsLoaded().goToProfile();
      profilePage.waitUntilPageIsLoaded();
      Assert.assertTrue(profilePage.checkingTitle());

  }
    @Test
    public  void profileUserGuestTest(){
      System.out.println("--------------------profileUserGuestTest is started--------------------");
      homePage.waitUntilPageIsLoaded().openLoginPage();
      loginPage.waitUntilPageIsLoaded().enterLoginPassword(LOGIN,PASSWORD);
      homePageAuth.waitUntilPageIsLoaded().goToProfile();
      profilePage.waitUntilPageIsLoaded();
        if(!profilePage.checkProfileStatus(guestStatus)){
        profilePage.goToEditProfilePage();
        editProfilePage.waitUntilPageIsLoaded().selectGuestStatus().saveProfile();
        profilePage.waitUntilPageIsLoaded();
      }
      profilePage.goToHomePageAuth();
      homePageAuth.waitUntilPageIsLoaded();
      System.out.println("User Status - "+guestStatus+" Add buttun is hidden - " + !homePageAuth.checkButtonAddEventIsDisplayed());
      Assert.assertFalse(homePageAuth.checkButtonAddEventIsDisplayed());
      System.out.println("--------------------profileUserGuestTest is finished--------------------");
    }

    @Test
    public void profileUserFamilyTest(){

      System.out.println("--------------------profileUserFamilyTest is started--------------------");
      homePage.waitUntilPageIsLoaded().openLoginPage();
      loginPage.waitUntilPageIsLoaded().enterLoginPassword(LOGIN,PASSWORD);
      homePageAuth.waitUntilPageIsLoaded().goToProfile();
      profilePage.waitUntilPageIsLoaded();
        if(!profilePage.checkProfileStatus(familyStatus)) {
        profilePage.goToEditProfilePage();
        editProfilePage.waitUntilPageIsLoaded().selectFamilyStatus().saveProfile();
        profilePage.waitUntilPageIsLoaded();
        }
      profilePage.goToHomePageAuth();
      homePageAuth.waitUntilPageIsLoaded();
      System.out.println("User status - " + familyStatus + " Add buttun is displayed - " + homePageAuth.checkButtonAddEventIsDisplayed());
      Assert.assertTrue(homePageAuth.checkButtonAddEventIsDisplayed());
      System.out.println("--------------------profileUserFamilyTest is finished--------------------");
    }

    @Test
    public void profileUserGuestAndFamilyTest(){

      System.out.println("--------------------profileUserGuestAndFamilyTest is started--------------------");
      homePage.waitUntilPageIsLoaded().openLoginPage();
      loginPage.waitUntilPageIsLoaded().enterLoginPassword(LOGIN,PASSWORD);
      homePageAuth.waitUntilPageIsLoaded().goToProfile();
      profilePage.waitUntilPageIsLoaded();
        if(!profilePage.checkProfileStatus(guestAndFamilyStatus)){
        profilePage.goToEditProfilePage();
        editProfilePage.waitUntilPageIsLoaded().selectGuestAndFamilyStatus().saveProfile();
        profilePage.waitUntilPageIsLoaded();
        }
      profilePage.goToHomePageAuth();
      homePageAuth.waitUntilPageIsLoaded();
      System.out.println("User status - " + guestAndFamilyStatus + " Add buttun is displayed - " + homePageAuth.checkButtonAddEventIsDisplayed());
      Assert.assertTrue(homePageAuth.checkButtonAddEventIsDisplayed());
      System.out.println("--------------------profileUserGuestAndFamilyTest is finished--------------------");
    }

}
