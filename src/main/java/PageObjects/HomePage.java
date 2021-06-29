package PageObjects;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.TapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
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
        return driver.findElement(settingsLabel).getText();
    }

    public String getTextOnMessageBox() {
        tapOnElement(messageBox);
        return driver.findElement(messageText).getText();
    }

    public void tapOnElement(By elementToTap){
        TouchAction t= new TouchAction(driver);
        t.tap(TapOptions.tapOptions().withElement(element(driver.findElement(elementToTap)))).perform();
    }
}

