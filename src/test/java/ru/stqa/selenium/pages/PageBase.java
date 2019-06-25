package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public abstract class PageBase {

  protected WebDriver driver;




  public String getTitle() {
    return driver.getTitle();
  }


  protected PageBase(WebDriver driver){
    this.driver = driver;
  }

  protected void waitUntilElementIsClickable(By locator, int time){
    try {
      new WebDriverWait(driver,time)
              .until(ExpectedConditions.elementToBeClickable(locator));
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  protected void waitUntilElementIsClickable(WebElement element, int time){
    try {
      new WebDriverWait(driver,time)
              .until(ExpectedConditions.elementToBeClickable(element));
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  protected void waitUntilElementIsVisible(By locator, int time){
    try {
      new WebDriverWait(driver,time)
              .until(ExpectedConditions.visibilityOfElementLocated(locator));
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  protected void waitUntilListElementsIsVisible(By locator, int time){
    try {
      new WebDriverWait(driver,time)
              .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  protected void waitUntilListElementsIsVisible(List<WebElement> list, int time){
    try {
      new WebDriverWait(driver,time)
              .until(ExpectedConditions.visibilityOfAllElements(list));
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  protected void waitUntilElementIsVisible(WebElement element, int time){
    try {
      new WebDriverWait(driver,time)
              .until(ExpectedConditions.visibilityOf(element));
    } catch(Exception e){
      e.printStackTrace();
    }
  }


  protected void waitUntilElemAttrContainsText(By locator, String attribute, String value, int time){
    try {
      new WebDriverWait(driver,time)
              .until(ExpectedConditions.attributeContains(locator,attribute,value));
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  protected void waitUntilElemAttrContainsText(WebElement element, String attribute, String value, int time){
    try {
      new WebDriverWait(driver,time)
              .until(ExpectedConditions.attributeContains(element,attribute,value));
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  protected void waitUntilElemContainsText(By locator, String value, int time){
    try {
      new WebDriverWait(driver,time)
              .until(ExpectedConditions.textToBePresentInElementLocated(locator,value));
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  protected void waitUntilElemContainsText(WebElement element, String value, int time){
    try {
      new WebDriverWait(driver,time)
              .until(ExpectedConditions.textToBePresentInElement(element,value));
    } catch(Exception e){
      e.printStackTrace();
    }
  }
  public void waitUntilPageIsLoadedJS(){

    WebDriverWait wait = new WebDriverWait(driver , 30);
    wait.until((ExpectedCondition<Boolean>) driver -> {
      System.out.println("Current windows State - " + String.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState")));
      return String.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState")).equals("complete");
    });

  }







}

