package resources;


import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import utilites.CommonUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ExtentManager {

	public static ExtentReports extent;
	public static ExtentHtmlReporter htmlReports;

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			String extentReportfileName = sdf.format(date);
			htmlReports = new ExtentHtmlReporter(
					new File(CommonUtils.filePathWithPathSeparator(System.getProperty("user.dir"),"TestResults","TestReports",extentReportfileName+"_Automation_Results.html")));
			extent = new ExtentReports();
			extent.attachReporter(htmlReports);
			extent.setAnalysisStrategy(AnalysisStrategy.TEST);
			htmlReports.config().setReportName("Siddharth test Execution Report");
			htmlReports.config().setTheme(Theme.STANDARD);
		}
		return extent;
	}
}

