package io.github.arvind142.framework.framework.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.model.Test;
import io.github.arvind142.framework.framework.constants.ReportingConstants;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SummaryGenerator {
    private static SummaryGenerator summaryGenerator = null;

    private SummaryGenerator() {

    }

    public static void generateSummary(ExtentReports extentReports) throws IOException {
        init();
        List<String> lines = new ArrayList<>();
        lines.add("Status\t\tTest Name");
        if (ReportingConstants.generateSummary) {
            for (Test test : extentReports.getReport().getTestList()) {
                lines.add(test.getStatus()+"\t\t"+test.getName()+System.lineSeparator());
            }
        }
        Files.write(Path.of(ReportingConstants.summaryOutputFilePath), lines, StandardCharsets.UTF_8);
    }

    private static void init() {
        if (summaryGenerator == null) {
            synchronized (SummaryGenerator.class) {
                summaryGenerator = new SummaryGenerator();
            }
        }
    }
}
