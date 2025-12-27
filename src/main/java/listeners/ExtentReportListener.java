package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import factory.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

 
    @Override
    public void onStart(ITestContext context) {

        // Create folder with timestamp for each run
        String timestamp = new java.text.SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new java.util.Date());
        String reportDir = System.getProperty("user.dir") + "/test-output/ExtentReport_" + timestamp;

        new File(reportDir).mkdirs();

        String reportPath = reportDir + "/index.html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

        // ---- BEST SETTINGS ----
        spark.config().setDocumentTitle("Automation Test Report");
        spark.config().setReportName("Selenium Test Execution Report");
        spark.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);

        // This works ONLY for inside test logs
        spark.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Add environment info
        extent.setSystemInfo("Executor", System.getProperty("user.name"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser", "Chrome");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (test.get() != null)
            test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        // Safe check
        if (test.get() != null) {
            test.get().log(Status.FAIL, result.getThrowable());
        }

        // Screenshot Section: avoid NPE if driver is null
        try {
            if (result.getStatus() == ITestResult.FAILURE) {

                File src = ((TakesScreenshot) DriverFactory.getDriver())
                        .getScreenshotAs(OutputType.FILE);

                File dest = new File("test-output/screenshots/"
                        + result.getMethod().getMethodName() + ".png");

                dest.getParentFile().mkdirs();
                Files.copy(src.toPath(), dest.toPath());

                if (test.get() != null) {
                    test.get().addScreenCaptureFromPath(dest.getAbsolutePath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (test.get() != null)
            test.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
