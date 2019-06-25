package ru.stqa.selenium.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageAuthHelper extends PageBase {

    @FindBy(id="profile")
    WebElement profileIcon;

    @FindBy(id="family")
    WebElement myFamilyIcon;

    @FindBy(id="idbtnaddevent")
    WebElement addEventButton;

    public HomePageAuthHelper(WebDriver driver) {

        super(driver);
    }

    public HomePageAuthHelper waitUntilPageIsLoaded(){

        waitUntilElementIsClickable(profileIcon,30);
        return this;
    }


    public void waitUntilPageIsLoadedJS(){

        WebDriverWait wait = new WebDriverWait(driver , 30);
        wait.until((ExpectedCondition<Boolean>) driver -> {
            System.out.println("Current windows State - " + String.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState")));
            return String.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState")).equals("complete");
        });
    }

    public boolean profileButtonTitleContainsText(String login) {

        return profileIcon.getAttribute("title").contains(login);
    }



    public HomePageAuthHelper goToProfile() {

        waitUntilPageIsLoaded();
        profileIcon.click();
        return this;
    }

    public HomePageAuthHelper goToMyFamilyPage() {

        waitUntilPageIsLoaded();
        myFamilyIcon.click();
        return this;
    }

    public boolean checkButtonAddEventIsDisplayed() {

        waitUntilPageIsLoaded();
        return addEventButton.isDisplayed();
    }

}








