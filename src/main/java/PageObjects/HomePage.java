package PageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

public class HomePage {
    private AndroidDriver driver = null;
    private final By homePageText = By.xpath("//android.widget.TextView[@text='ReferenceAndroid']");

    public HomePage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
    }

    public boolean isAppLaunched() {
        return driver.findElement(homePageText).isDisplayed();
    }
}

