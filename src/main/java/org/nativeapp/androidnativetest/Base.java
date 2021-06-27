package org.nativeapp.androidnativetest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import resources.ExtentManager;
import utilites.PropertyReader;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    public static AppiumDriverLocalService service;
    public static AndroidDriver<AndroidElement> driver;
    public static ExtentReports extent;
    public static ExtentTest childTest;
    private static File propertyFile = new File(System.getProperty("user.dir") + "/propertyFiles");
    private static String projectsetupFile = "project.properties";

    public AppiumDriverLocalService startServer() {
        boolean flag = checkIfServerIsRunnning(4723);
        if (!flag) {
            service = AppiumDriverLocalService.buildDefaultService();
            service.start();
        }
        return service;
    }

    public static boolean checkIfServerIsRunnning(int port) {
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);

            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

    public static void startEmulator() throws IOException, InterruptedException {
        Runtime.getRuntime().exec(System.getProperty("user.dir") + "/src/main/java/resources/startEmulator.sh");
        Thread.sleep(6000);
    }

    public static AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException {
        PropertyReader prop = new PropertyReader();
        Properties property = prop.getProperty(propertyFile, projectsetupFile);

        File appDir = new File("apps//");
        File app = new File(appDir, property.getProperty(appName));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String device = property.getProperty("deviceName");
        if (device.contains("Emulator")) {
            startEmulator();
        }
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public void setStoryNumberForReporting(String testSuitName) {
        extent = ExtentManager.getReporter();
        childTest = extent.createTest(testSuitName);
    }
}

