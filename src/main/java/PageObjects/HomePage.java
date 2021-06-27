package PageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

public class HomePage {
    private AndroidDriver driver = null;
    private final By homePageTitle = By.xpath("//android.widget.TextView[@text='ReferenceAndroid']");
    private final By homePageText = By.xpath("//android.widget.TextView[@text='Hello World!']");
    private final String moreOptions = "More options";
    private final By settingsLabel = By.id("com.abnamro.apps.referenceandroid:id/title");
    private final By messageBox = By.id("com.abnamro.apps.referenceandroid:id/fab");
    private final By messageText = By.id("com.abnamro.apps.referenceandroid:id/snackbar_text");

    public HomePage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
    }

    public boolean isAppLaunched() {
        return driver.findElement(homePageTitle).isDisplayed();
    }

    public boolean isHomepageTextDisplayed() {
        return driver.findElement(homePageText).isDisplayed();
    }

    public String getTextFromMoreOptions() {
        driver.findElementByAccessibilityId(moreOptions).click();
        String labelText = driver.findElement(settingsLabel).getText();
        return labelText;
    }

    public String getTextOnMessageBox() {
        driver.findElement(messageBox).click();
        return driver.findElement(messageText).getText();
    }
}

