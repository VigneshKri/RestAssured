package api.test;

//Extent report 5.x

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener
{
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    public void onStart(ITestContext testContext) {
        System.out.println("Extent test started");
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        // Use absolute path for reports directory
        String reportDir = System.getProperty("user.dir") + java.io.File.separator + "reports";
        java.io.File dir = new java.io.File(reportDir);
        if (!dir.exists()) dir.mkdirs();
        String reportPath = reportDir + java.io.File.separator + repName;
        sparkReporter = new ExtentSparkReporter(reportPath);

        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");
        sparkReporter.config().setReportName("Pet Store Users API");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Pet Store Users API");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user", "stark");
    }


    public void onTestSuccess(ITestResult result) {
        ExtentTest test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, "Test Passed");
        result.setAttribute("extentTest", test);
    }

    public void onTestFailure(ITestResult result) {
        ExtentTest test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, "Test Failed");
        if (result.getThrowable() != null) {
            test.log(Status.FAIL, result.getThrowable().getMessage());
        }
        result.setAttribute("extentTest", test);
    }

    public void onTestSkipped(ITestResult result) {
        ExtentTest test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, "Test Skipped");
        if (result.getThrowable() != null) {
            test.log(Status.SKIP, result.getThrowable().getMessage());
        }
        result.setAttribute("extentTest", test);
    }

    public void onFinish(ITestContext testContext)
    {
        extent.flush();
    }

}