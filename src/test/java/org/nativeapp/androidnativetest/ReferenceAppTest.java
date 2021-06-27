package org.nativeapp.androidnativetest;

import PageObjects.HomePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.io.IOException;

public class ReferenceAppTest extends Base {
    private HomePage homePage;

    @BeforeClass
    public void classSetup()
    {
        setStoryNumberForReporting("referenceAndroidAppTestSuit");
    }

    @BeforeTest
    public void startAppiumServer() throws IOException, InterruptedException {
        service = startServer();
        AndroidDriver<AndroidElement> driver = capabilities("referenceAndroidApp");
        homePage = new HomePage(driver);
    }

    @Test
    public void validateAppWasLaunchedSuccessfully(){
        Assert.assertTrue(homePage.isAppLaunched());
    }

    @Test
    public void validateHomePageTextIsDisplayed(){
        Assert.assertTrue(homePage.isHomepageTextDisplayed());
    }

    @Test
    public void validateTheTextDisplayedOnTheMessageBox(){
        Assert.assertEquals(homePage.getTextOnMessageBox(),"Replace with your own action");
    }

    @Test
    public void validateTheTextOnMoreOptions(){
        Assert.assertEquals(homePage.getTextFromMoreOptions(),"Settings");
    }

    @AfterTest
    public void stopAppiumServer() {
        driver.quit();
        service.stop();
    }
}

