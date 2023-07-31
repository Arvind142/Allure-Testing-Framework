package io.github.arvind142.framework.framework.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Test;
import io.github.arvind142.framework.framework.constants.ReportingConstants;
import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
class SummaryGenerator {
    private static SummaryGenerator summaryGenerator = null;

    private SummaryGenerator() {

    }

    public static void generateSummary(ExtentReports extentReport){
        init();
        List<String> lines = new ArrayList<>();
        if (ReportingConstants.generateSummary) {
            try{
                int pass = 0;
                int fail = 0;
                int skip = 0;
                int warn = 0;
                int info = 0;
                List<String> testList = new ArrayList<>();
                for(Test tests: extentReport.getReport().getTestList()){
                    // for test Summary
                    testList.add((System.lineSeparator()+tests.getStatus()+"\t\t"+ tests.getName()));
                    // for Summary
                    pass = (tests.getStatus() == Status.PASS) ? (pass + 1) : pass;
                    fail = (tests.getStatus() == Status.FAIL) ? (fail + 1) : fail;
                    skip = (tests.getStatus() == Status.SKIP) ? (skip + 1) : skip;
                    warn = (tests.getStatus() == Status.WARNING) ? (warn + 1) : warn;
                    info = (tests.getStatus() == Status.INFO) ? (info + 1) : info;
                }

                // write overall summary :)
                lines.add("||------------------|| SUMMARY ||------------------||");
                lines.add(("\r\nPASS ➡➡ "+pass));
                lines.add(("\r\nFAIL ➡➡ "+fail));
                lines.add(("\r\nSKIP ➡➡ "+skip));
                lines.add(("\r\nWARN ➡➡ "+warn));
                lines.add(("\r\nINFO ➡➡ "+info));

                // few new lines :)
                lines.add(System.lineSeparator());
                lines.add(System.lineSeparator());

                // write test summary
                lines.add("||------------------|| Test Summary ||------------------||");
                lines.addAll(testList);
                Files.write(Path.of(ReportingConstants.summaryOutputFilePath), lines, StandardCharsets.UTF_8);
            }
            catch (Exception e){
                log.error("Summary creation failed due to: "+e.getMessage());
            }
        }
    }

    private static void init() {
        if (summaryGenerator == null) {
            synchronized (SummaryGenerator.class) {
                summaryGenerator = new SummaryGenerator();
            }
        }
    }
}
