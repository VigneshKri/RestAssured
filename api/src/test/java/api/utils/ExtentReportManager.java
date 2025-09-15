package api.utils;

//Extent report 5.x


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.response.Response;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportManager {
    private static ExtentReports extentTest;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void initReports() {
        if (extentTest == null) {
            String reportPath = PropertyHelper.getReportPath() + "ExtentReport_" +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".html";
            new File(PropertyHelper.getReportPath()).mkdirs();

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("Pet Store API");
            sparkReporter.config().setReportName("REST API tests");

            extentTest = new ExtentReports();
            extentTest.attachReporter(sparkReporter);
            extentTest.setSystemInfo("Environment", "QA");
            extentTest.setSystemInfo("Base URL", PropertyHelper.getProperty("base-url"));
        }
    }

    public static void createTest(String testName, String description) {
        if (extentTest == null) {
            throw new IllegalStateException("Extent reports not initialised");
        }
        ExtentTest extentTest1 = extentTest.createTest(testName, description);
        test.set(extentTest1);
    }

    public static void logInfo(String message) {
        ExtentTest currentTest = test.get();
        if (currentTest == null) {
            throw new IllegalStateException("Call createTest() dumbass");
        }
        currentTest.log(Status.INFO, message);
    }

    public static void logInPass(String message) {
        ExtentTest currentTest = test.get();
        if (currentTest == null) {
            throw new IllegalStateException("Call createTest() dumbass");
        }
        currentTest.log(Status.PASS, message);
    }

    public static void logFail(String message) {
        ExtentTest currentTest = test.get();
        if (currentTest == null) {
            throw new IllegalStateException("Call createTest() dumbass");
        }
        currentTest.log(Status.FAIL, message);
    }

    public static void logSkip(String message) {
        ExtentTest currentTest = test.get();
        if (currentTest == null) {
            throw new IllegalStateException("Call createTest() dumbass");
        }
        currentTest.log(Status.SKIP, message);
    }

    public static void logResponse(Response response) {
        ExtentTest currentTest = test.get();
        if (currentTest == null) {
            throw new IllegalStateException("Call createTest() dumbass");
        }

        String responseInfo = String.format(
                "Response Code: %d, Reponse Body: %s",
                response.getStatusCode(),
                response.getBody()
        );
        currentTest.log(Status.INFO, responseInfo);
    }

    public static void flushReports() {
        if (extentTest != null) {
            extentTest.flush();
        }
    }
}