package ru.stqa.selenium.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePageHelper extends PageBase {


    @FindBy(id="idsignin")
    WebElement loginButton;

    @FindBy(xpath = "//span[contains(text(),'Home')]")
    WebElement homeIcon;

    @FindBy(xpath = "//span[contains(text(),'Login')]")
    WebElement loginIcon;

    @FindBy(xpath = "//span[contains(text(),'Registration')]")
    WebElement registrationIcon;

    public HomePageHelper(WebDriver driver) {

        super(driver);
    }

    public HomePageHelper waitUntilPageIsLoaded(){

        waitUntilElementIsClickable(loginButton,20);
        return this;
    }

    public void waitUntilPageIsLoadedJS(){

        WebDriverWait wait = new WebDriverWait(driver , 30);
        wait.until((ExpectedCondition<Boolean>) driver -> {
            System.out.println("Current windows State - " + String.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState")));
            return String.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState")).equals("complete");
        });
    }

    public HomePageHelper openLoginPage() {

        loginButton.click();
        return this;
    }

    public boolean homeIconIsDisplayed() {

        return homeIcon.isDisplayed();
    }

    public boolean loginIconIsDisplayed() {

        return loginIcon.isDisplayed();
    }

    public boolean registrationIconIsDisplayed() {

        return registrationIcon.isDisplayed();
    }

}
