package io.github.arvind142.framework.constants;

public class ReportingConstants {

    public static final String RESULT_OUTPUT_FOLDER = "./test-output/";
    public static final boolean GENERATE_SUMMARY = true;
    private static final String SUMMARY_OUTPUT_FILE_NAME = "TestSummary.txt";
    public static final String SUMMARY_OUTPUT_FILE_PATH = RESULT_OUTPUT_FOLDER + SUMMARY_OUTPUT_FILE_NAME;

    private static final String SCREENSHOT_FOLDER_NAME = "snapshots";
    public static final String SCREENSHOT_FOLDER = RESULT_OUTPUT_FOLDER + SCREENSHOT_FOLDER_NAME;
    public static final String HTML_REPORT_NAME ="Test Result.html";

    private ReportingConstants() {
    }
}
