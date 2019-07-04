package ru.stqa.selenium.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static ru.stqa.selenium.TestsBase.LOGIN;


public class ProfilePageHelper extends PageBase {



    @FindBy(id="fieldobjconfession")
    WebElement confessionProfileData;

    @FindBy(id="fieldobjlanguages")
    WebElement languageProfileData;

    @FindBy(id="fieldobjfoodPreferences")
    WebElement foodsProfileData;

    @FindBy(id="titleprofile")
    WebElement titleProfile;

    @FindBy(className = "itemprofilefit")
    List<WebElement> itemsListMyFamiliProfile;

    @FindBy(id="ihome")
    WebElement homeIcon;

    @FindBy(id="statusinmishpahuginprofile")
    WebElement userStatusData;

    @FindBy(id="idbtneditprofile")
    WebElement editProfileButton;



    public ProfilePageHelper(WebDriver driver) {

        super(driver);
    }



    public boolean checkingTitle() {

        waitUntilElemContainsText(titleProfile,LOGIN,30);
        return titleProfile.getText().contains(LOGIN);
    }

    public ProfilePageHelper waitUntilPageIsLoaded() {

        waitUntilListElementsIsVisible(itemsListMyFamiliProfile.subList(5,13),30);
        return this;
    }

    public void waitUntilPageIsLoadedJS(){

        WebDriverWait wait = new WebDriverWait(driver , 30);
        wait.until((ExpectedCondition<Boolean>) driver -> {
            System.out.println("Current windows State - " + String.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState")));
            return String.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState")).equals("complete");
        });
    }

    public boolean checkProfileStatus(String status) {

      //  waitUntilElemContainsText(userStatusData,status,30);
        return userStatusData.getText().equals(status);
    }


    public ProfilePageHelper goToEditProfilePage() {

        waitUntilElementIsVisible(titleProfile,30);
        editProfileButton.click();
        return this;
    }

    public ProfilePageHelper goToHomePageAuth() {

        homeIcon.click();
        return this;
    }


    public String confessionProfileData(){

      return confessionProfileData.getText();
    }

    public String languageProfileData() {

        return languageProfileData.getText();
    }


    public String foodsProfileData() {

        return foodsProfileData.getText();
    }


}
