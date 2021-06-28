package resources;


import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import org.apache.log4j.Logger;
import org.nativeapp.androidnativetest.Base;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilites.CommonUtils;

import java.io.IOException;

public class TestListener extends Base implements ITestListener {

	public static Logger log = Logger.getLogger(Base.class.getSimpleName());
	
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	public void onStart(ITestContext iTestContext) {
		log.info("I am in onStart method " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", this.driver);
	}

	public void onFinish(ITestContext iTestContext) {
		log.info("I am in onFinish method " + iTestContext.getName());
		ExtentManager.getReporter().flush();
	}

	public void onTestStart(ITestResult iTestResult) {
		log.info("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
	}

	public void onTestSuccess(ITestResult iTestResult) {
		log.info("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
		childTest.log(Status.PASS,
				MarkupHelper.createLabel("Test case : " + iTestResult.getName() + " is passed", ExtentColor.GREEN));

	}

	public void onTestFailure(ITestResult iTestResult) {
		log.info("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
		try {
			String screenshot = CommonUtils.captureScreenshot(driver, CommonUtils.generateFileName(iTestResult));
			childTest.log(Status.FAIL, MarkupHelper.createLabel("Test Cases is failed due to:", ExtentColor.RED));
			childTest.log(Status.ERROR, iTestResult.getThrowable());
			childTest.log(Status.FAIL, "Screenshot captured::" + childTest.addScreenCaptureFromPath(screenshot));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult iTestResult) {
		log.info("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
		childTest.log(Status.SKIP, MarkupHelper.createLabel("Test Cases is Skipped", ExtentColor.ORANGE));
		childTest.log(Status.SKIP, "Test case :" + iTestResult.getName() + " is skipped.");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}

}
