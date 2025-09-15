package api;

import api.utils.ExtentReportManager;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class BaseTest {
    @BeforeSuite
    public void setupSuite() {
        ExtentReportManager.initReports();
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        String testName = method.getName();
        String description = "Auto" + testName;
        ExtentReportManager.createTest(testName, description);
        ExtentReportManager.logInfo("Test started: " + testName);
    }

    @AfterMethod
    public void afterMethod() {
        ExtentReportManager.logInfo("Test completed");
    }

    @AfterSuite
    public void tearDown() {
        ExtentReportManager.flushReports();
    }
}
