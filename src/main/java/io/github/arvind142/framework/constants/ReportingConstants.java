package io.github.arvind142.framework.constants;

public class ReportingConstants {

    public static final String resultOutputFolder = "./test-output/";
    public static final boolean generateSummary = true;
    private static final String summaryOutputFileName = "TestSummary.txt";
    public static final String summaryOutputFilePath = resultOutputFolder+summaryOutputFileName;

    private static final String screenshotFolderName = "snapshots";
    public static final String screenshotFolder=resultOutputFolder+screenshotFolderName;
    public static final String htmlReportName="Test Result.html";

    private ReportingConstants() {
    }
}
