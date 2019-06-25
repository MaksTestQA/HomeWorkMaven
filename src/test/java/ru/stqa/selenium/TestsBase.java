package ru.stqa.selenium;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.selenium.factory.WebDriverPool;

/**
 * Base class for TestNG-based test classes
 */
public class TestsBase {

  protected static URL gridHubUrl = null;
  protected static String baseUrl;
  protected static Capabilities capabilities;

  public static final String LOGIN = "maks";
  public static final String PASSWORD = "vfrcbv87";
  private static final String URL = "https://mishpahug.co.il/";
  protected static  String familyStatus = "Family";
  protected static  String guestStatus = "Guest";
  protected static  String guestAndFamilyStatus = "Guest and Family";

  protected WebDriver driver;



  @BeforeSuite
  public void initTestSuite() throws IOException {
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
      gridHubUrl = new URL(config.getProperty("grid.url"));
    }
    capabilities = config.getCapabilities();
  }

  @BeforeMethod
  public void initWebDriver() throws InterruptedException {
    driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
    driver.get(baseUrl);
    driver.manage().window().maximize();
    Thread.sleep(2000);
    driver.findElement(By.id("closedIntro")).click();

  }

  @AfterMethod
  public void stopDriver(){
    driver.close();
  }



  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    WebDriverPool.DEFAULT.dismissAll();
  }
}


