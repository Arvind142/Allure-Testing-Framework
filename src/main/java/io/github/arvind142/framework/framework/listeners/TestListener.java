package io.github.arvind142.framework.framework.listeners;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class TestListener implements ITestListener {

    public void onTestStart(ITestResult result) {
        log.trace("onTestStart");
    }

    public void onTestSuccess(ITestResult result) {
        log.trace("onTestSuccess");
    }

    public void onTestFailure(ITestResult result) {
        log.trace("onTestFailure");
    }

    public void onTestSkipped(ITestResult result) {
        log.trace("onTestSkipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.trace("onTestFailedButWithinSuccessPercentage");
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
        log.trace("onStart");
    }

    public void onFinish(ITestContext context) {
        log.trace("onFinish");
    }
}
