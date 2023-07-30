package io.github.arvind142.framework.framework.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.model.Test;
import io.github.arvind142.framework.framework.constants.ReportingConstants;

public class SummaryGenerator {
    private static SummaryGenerator summaryGenerator = null;

    private SummaryGenerator() {

    }

    public static void generateSummary(ExtentReports extentReports) {
        init();
        String text = "";
        if (ReportingConstants.generateSummary) {
            for (Test test : extentReports.getReport().getTestList()) {
                System.out.println(test.getName());
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
