package utilites;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.nativeapp.androidnativetest.Base;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils extends Base {

    public static String captureScreenshot(AppiumDriver driver, String screenName) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        Date date = new Date();
        String fileName = sdf.format(date);
        TakesScreenshot screen = (TakesScreenshot) driver;
        File src = screen.getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") + filePathWithPathSeparator(System.getProperty("file.separator"),
                "TestResults", "Screenshots", fileName + screenName + ".png");
        File target = new File(dest);
        FileUtils.copyFile(src, target);
        return dest;
    }

    public static String generateFileName(ITestResult result) {
        Date date = new Date();
        return result.getName() + "_" + DateFormat.DATE_FIELD;
    }

    public static String filePathWithPathSeparator(String... paths) {
        if (paths == null || paths.length < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String path : paths) {
            if (sb.length() > 0) {
                sb.append(System.getProperty("file.separator"));
            }
            sb.append(path);
        }
        return sb.toString();
    }
}
