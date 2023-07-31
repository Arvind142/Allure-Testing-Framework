package io.github.arvind142.framework.framework.builder;

import com.aventstack.extentreports.Status;
import io.github.arvind142.framework.framework.constants.IconConstants;
import io.github.arvind142.framework.framework.factory.ConfigFactory;
import io.github.arvind142.framework.framework.reporter.TestReporter;

public class DriverBuilder {
    public static void init(){
        // shell
        TestReporter.assignDevice(
                ConfigFactory.getBrowserProperties().getProperty("BrowserName")
        );
        TestReporter.log(Status.INFO,IconConstants.Browser.getBrowserIcon(ConfigFactory.getBrowserProperties().getProperty("BrowserName"))+" Browser Driver Initialized");
    }
}
