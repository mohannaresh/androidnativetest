package com.nativeapptest;

import PageObjects.HomePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import nativeapp.Base;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ReferenceAppTest extends Base {
    @BeforeTest
    public void startAppiumServer() {
        service = startServer();
    }

    @Test
    public void validateAppLaunchedSuccessfully() throws IOException, InterruptedException {
        AndroidDriver<AndroidElement> driver = capabilities("referenceAndroidApp");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isAppLaunched());
        Assert.assertTrue(homePage.isHomepageTextDisplayed());
        Assert.assertEquals(homePage.getTextOnMessageBox(),"Replace with your own action");
        Assert.assertEquals(homePage.getTextFromMoreOptions(),"Settings");
    }

    @AfterTest
    public void stopAppiumServer() {
        service.stop();
    }
}

