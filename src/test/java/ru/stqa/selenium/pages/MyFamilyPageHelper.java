package ru.stqa.selenium.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MyFamilyPageHelper extends PageBase {

    @FindBy(id="fieldobjconfession")
    WebElement confessionMyFamilyData;

    @FindBy(id="fieldobjlanguages")
    WebElement languageMyFamilyData;

    @FindBy(id="fieldobjfoodPreferences")
    WebElement foodsMyFamilyData;

    @FindBy(className = "itemprofilefit")
    List<WebElement> itemsListMyFamiliProfile;

    @FindBy(id="idcontenteventsthisfamilyrow")
    WebElement familyEventsArea;


    public MyFamilyPageHelper(WebDriver driver) {

        super(driver);
    }


    public MyFamilyPageHelper waitUntilPageIsLoaded() {

        waitUntilListElementsIsVisible(itemsListMyFamiliProfile,30);
        waitUntilElementIsVisible(familyEventsArea,30);
        return this;
    }

    public void waitUntilPageIsLoadedJS(){

        WebDriverWait wait = new WebDriverWait(driver , 30);
        wait.until((ExpectedCondition<Boolean>) driver -> {
            System.out.println("Current windows State - " + String.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState")));
            return String.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState")).equals("complete");
        });
    }



    public String confessionMyFamilyData(){
        waitUntilElemContainsText(confessionMyFamilyData,"Irreligious",10);
        return confessionMyFamilyData.getText();
    }

    public String languageMyFamilyData() {
        waitUntilElemContainsText(languageMyFamilyData,"Russian",10);
        return languageMyFamilyData.getText();
    }


    public String foodsMyFamilyData() {
        waitUntilElemContainsText(foodsMyFamilyData,"No kosher",10);
        return foodsMyFamilyData.getText();
    }

}
