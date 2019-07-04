package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Set;


public class HomePageHelper extends PageBase {

    @FindBy(xpath = "//div[@class='itemEventInsert']")
    List<WebElement> listEvents;

    @FindBy(name="selectholidays")
    WebElement filterHoliday;

    @FindBy(name="selectconfession")
    WebElement filterConfession;

    @FindBy(className = "moreItemEvents")
    WebElement viewMoreButton;

    @FindBy(className = "moreItemEvents")
    public List<WebElement>viewMoreButtonList;

    public String mainHahdle = driver.getWindowHandle();




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
        waitUntilListElementsIsVisible(listEvents, 20);
        return this;
    }

    public void waitUntilPageIsLoadedJS(){

        WebDriverWait wait = new WebDriverWait(driver , 30);
        wait.until((ExpectedCondition<Boolean>) driver -> {
            System.out.println("Current windows State - " + String.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState")));
            return String.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState")).equals("complete");
        });
    }

    public void areAllEventsByFilterHoliday(String by_filter) {

        int counter = 0;
        for (WebElement element : viewMoreButtonList){

            element.click();
            waitUntilNumberOfWindowsToBe(2,30);
            goToSecondWindow(mainHahdle);
            sleep(3000);
            if(EventNewWindowHelper.holidayEventData.getText().contains(by_filter))counter++;
            closeSecondWindowAndBackToMain(mainHahdle);
        }
        Assert.assertTrue(counter == viewMoreButtonList.size());


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

    public HomePageHelper chooseFilterHoliday(String value) {

        Select selector = new Select(filterHoliday);
       selector.selectByValue(value);
     //   selector.selectByVisibleText("Shabbat (4)");
        return this;
    }

    public HomePageHelper waitEventsListReloaded() {
        try{
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOfAllElements(listEvents));
        } catch(Exception e){
            try {
                Thread.sleep(1000);
                waitUntilListElementsIsVisible(listEvents,20);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return this;
    }

    public boolean isEventsHoliday(String value) {

        int counter = 0;
        for(WebElement element: listEvents){
            if(element.findElement(By.className("holidayItemEvents")).getText().equals(value) )counter++;
        }
        //System.out.println(counter);
        return counter == listEvents.size();
    }

    public boolean isEventsConfession(String value) {

        int counter = 0;
        for(WebElement element: listEvents){
            //System.out.println(element.getSize());
          //  System.out.println(element.getText());
            if(element.getText().contains(value))
           counter++;
        }
       // System.out.println(counter);
        return counter == listEvents.size();
    }

    public HomePageHelper chooseFilterConfession(String value) {

        Select select = new Select(filterConfession);
        select.selectByValue(value);
        return this;

    }

    public boolean isEventsHolidayAndConfession(String holiday, String confession) {

        return isEventsHoliday(holiday) && isEventsConfession(confession);
    }

    public HomePageHelper sleep(int value){

        try {
            Thread.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }



    public HomePageHelper closeSecondWindowAndBackToMain(String mainHandle) {
        driver.close();
        driver.switchTo().window(mainHandle);
        return this;
    }

    public  HomePageHelper goToSecondWindow(String mainHandle) {

        //String mainHandle = driver.getWindowHandle();
        Set<String> setHandles = driver.getWindowHandles();
       // System.out.println("setHandles: "+setHandles);
        String anotherHandle ="";
        for(String handle: setHandles){
            if(!handle.equals(mainHandle))anotherHandle=handle;
        }
        driver.switchTo().window(anotherHandle);

        return this;
    }

    public HomePageHelper waitUntilNumberOfWindowsToBe(int num, int time){
        try {
            new WebDriverWait(driver,time)
                    .until(ExpectedConditions.numberOfWindowsToBe(num));
        } catch(Exception e){
            e.printStackTrace();
        }
        return this;
    }


}
