package ru.stqa.selenium.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditProfilePageHelper extends PageBase {

    @FindBy(xpath="//div[@class='centertext']")
    WebElement editProfileTitle;

    @FindBy(id="typeuser2inprofile")
    WebElement familyStatusButton;

    @FindBy(id="typeuser1inprofile")
    WebElement guestStatusButton;

    @FindBy(id="typeuser3inprofile")
    WebElement guestAndFamilyButton;

    @FindBy(xpath="//i[@class='fa fa-check-square-o']")
    WebElement checkPictureInButtonSave;

    @FindBy(id="idbtnsaveprofile")
    WebElement saveButtonProfile;




    public EditProfilePageHelper(WebDriver driver) {

        super(driver);
    }


    public EditProfilePageHelper waitUntilPageIsLoaded() {

        waitUntilElemContainsText(editProfileTitle,"Edit profile! Please enter data in fields!",20);
        return this;
    }

    public void waitUntilPageIsLoadedJS(){

        WebDriverWait wait = new WebDriverWait(driver , 30);
        wait.until((ExpectedCondition<Boolean>) driver -> {
            System.out.println("Current windows State - " + String.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState")));
            return String.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState")).equals("complete");
        });
    }

    public EditProfilePageHelper selectFamilyStatus() {

        familyStatusButton.click();
        waitUntilElemAttrContainsText((familyStatusButton),
                "class","active",20);
        return this;
    }

    public EditProfilePageHelper saveProfile() {

        waitUntilElementIsVisible(checkPictureInButtonSave,20);
        saveButtonProfile.click();
        return this;
    }

    public EditProfilePageHelper selectGuestStatus() {

        guestStatusButton.click();
        waitUntilElemAttrContainsText(guestStatusButton,"class","active",20);
        return this;
    }

    public EditProfilePageHelper selectGuestAndFamilyStatus(){

        guestAndFamilyButton.click();
        waitUntilElemAttrContainsText(guestAndFamilyButton,"class","active",20);
        return this;
    }

}
