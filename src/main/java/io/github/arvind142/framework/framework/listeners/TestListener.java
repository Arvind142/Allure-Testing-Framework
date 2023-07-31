package io.github.arvind142.framework.framework.listeners;

import com.aventstack.extentreports.Status;
import io.github.arvind142.framework.framework.constants.IconConstants;
import io.github.arvind142.framework.framework.reporter.TestReporter;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class TestListener implements ITestListener {

    public void onTestStart(ITestResult result) {
        log.trace("onTestStart");
        TestReporter.createTest(result);
    }

    public void onTestSuccess(ITestResult result) {
        log.trace("onTestSuccess");
        TestReporter.log(Status.PASS, IconConstants.TestStatus.getStatusIcon(Status.PASS)+" Test Pass");
    }

    public void onTestFailure(ITestResult result) {
        log.trace("onTestFailure");
        TestReporter.logError(result.getThrowable());
        TestReporter.log(Status.FAIL, IconConstants.TestStatus.getStatusIcon(Status.FAIL)+" Test Fail");
    }

    public void onTestSkipped(ITestResult result) {
        log.trace("onTestSkipped");
        TestReporter.log(Status.SKIP, IconConstants.TestStatus.getStatusIcon(Status.SKIP)+" Test Skip");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.trace("onTestFailedButWithinSuccessPercentage");
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
        log.trace("onStart");
        TestReporter.init(context);
    }

    public void onFinish(ITestContext context) {
        log.trace("onFinish");
        TestReporter.flushReporting(context);
    }
}
