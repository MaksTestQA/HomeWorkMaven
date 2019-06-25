package ru.stqa.selenium.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;


public class LoginPageHelper extends PageBase {

    @FindBy(id="signinrequest")
    WebElement signInButton;

    @FindBy(id="logininput")
    WebElement loginField;

    @FindBy(id="passwordinput")
    WebElement passwordField;

    @FindBy(id="wrongloginorpassword")
    WebElement wrongAuthorizationMessage;

    @FindBy(id="closedsignin")
    WebElement closeAuthWindowButton;



    public LoginPageHelper(WebDriver driver) {

        super(driver);
    }

    public LoginPageHelper waitUntilPageIsLoaded() {

        waitUntilElementIsClickable(signInButton, 20);
        return this;
    }

    public void waitUntilPageIsLoadedJS(){

        WebDriverWait wait = new WebDriverWait(driver , 30);
        wait.until((ExpectedCondition<Boolean>) driver -> {
           // System.out.println("Current windows State - " + String.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState")));
            return String.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState")).equals("complete");
        });
    }


    public LoginPageHelper enterLoginPassword(String login, String password) {

        loginField.click();
        loginField.clear();
        loginField.sendKeys(login);
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
        signInButton.click();
        return this;
    }


    public boolean wrongMessageIsDisplayed() {

        //waitUntilElemContainsText(wrongAuthorizationMessage, "Wrong Authorization!", 10);
        waitUntilPageIsLoadedJS();
        return wrongAuthorizationMessage.getText().contains("Wrong Authorization!");
    }


    public LoginPageHelper cancelPopUpWindow() throws AWTException {

        Robot robot = new Robot();

        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    return this;
    }




}
