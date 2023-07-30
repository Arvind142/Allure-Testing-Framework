package io.github.arvind142.framework.framework.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class Reporter {
    private static Reporter reporter=null;

    private ExtentReports extentReports;

    private Reporter(){}


    static Reporter init(){
        if(reporter==null){
            synchronized (Reporter.class){
                reporter = new Reporter();
            }
        }
        return reporter;
    }
    ExtentReports getExtentReports(){
        return this.extentReports;
    }

    void flushReporting(){
        this.extentReports.flush();
    }

    void setSystemVar(String key, String value) {
        log.trace("Add System variable {} : {}",key,value);
        extentReports.setSystemInfo(key,value);
    }
}
