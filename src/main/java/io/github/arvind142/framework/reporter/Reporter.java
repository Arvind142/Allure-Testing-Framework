package io.github.arvind142.framework.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.arvind142.framework.constants.ReportingConstants;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
class Reporter {
    private static Reporter reporter=null;

    private ExtentReports extentReport;

    private ExtentSparkReporter htmlReporter;

    private String reportingFolder="";

    private Reporter(){
        log.debug("HTML Reporter called");
        String reportingFolder = ReportingConstants.resultOutputFolder;
        log.debug("Output folder created @ "+reportingFolder);
        // creating screenshot folder
        String assetFolder = ReportingConstants.screenshotFolder;
        File folder = new File(assetFolder);
        log.debug((folder.mkdirs() ? "screenshot folder created" : "screenshot folder creation failed"));
        log.debug("Asset folder created @ " + folder.getAbsolutePath());

        // reporting initialized
        htmlReporter = new ExtentSparkReporter(reportingFolder + ReportingConstants.htmlReportName);
        this.reportingFolder = reportingFolder;
        extentReport = new ExtentReports();
        // attach reporter :)
        extentReport.attachReporter(htmlReporter);
        log.debug("html reporting initialized");
    }


    static Reporter init(){
        if(reporter==null){
            synchronized (Reporter.class){
                reporter = new Reporter();
            }
        }
        return reporter;
    }
    ExtentReports getExtentReports(){
        return this.extentReport;
    }

    void flushReporting(){
        this.extentReport.flush();
        SummaryGenerator.generateSummary(extentReport);
    }

    void setSystemVar(String key, String value) {
        log.trace("Add System variable {} : {}",key,value);
        extentReport.setSystemInfo(key,value);
    }

}
