package io.github.arvind142.framework.framework.listeners;

import io.github.arvind142.framework.framework.constants.ReportingConstants;
import org.testng.ISuite;
import org.testng.internal.Utils;
import org.testng.reporters.XMLReporter;
import org.testng.reporters.XMLReporterConfig;
import org.testng.reporters.XMLStringBuffer;
import org.testng.xml.XmlSuite;

import java.util.Date;
import java.util.List;
import java.util.Properties;

public class XMLTestReporter extends XMLReporter {

    /**
     * @param config       The reporter config
     * @param attributes   The properties
     * @param minStartDate The minimum start date
     * @param maxEndDate   The maximum end date
     * @deprecated - This method stands deprecated as of TestNG <code>7.8.0</code> Add started-at,
     * finished-at and duration-ms attributes to the <code>&lt;suite&gt;</code> tag
     */
    @Deprecated
    public static void addDurationAttributes(
            XMLReporterConfig config, Properties attributes, Date minStartDate, Date maxEndDate) {
        setDurationAttributes(config, attributes, minStartDate, maxEndDate);
    }

    @Override
    public void generateReport(
            List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        getConfig().setOutputDirectory(ReportingConstants.resultOutputFolder);
        if (Utils.isStringEmpty(getConfig().getOutputDirectory())) {
            getConfig().setOutputDirectory(outputDirectory);
        }

        // Calculate passed/failed/skipped
        Count count = Count.Builder.builder().build();
        for (ISuite s : suites) {
            count.add(computeCountForSuite(s));
        }

        XMLStringBuffer rootBuffer = new XMLStringBuffer();
        Properties p = writeSummaryCount(count, rootBuffer);
        rootBuffer.push(XMLReporterConfig.TAG_TESTNG_RESULTS, p);
        writeReporterOutput(rootBuffer);
        for (ISuite suite : suites) {
            writeSuite(rootBuffer, suite);
        }
        rootBuffer.pop();
        Utils.writeUtf8File(
                getConfig().getOutputDirectory(), fileName(), rootBuffer, null /* no prefix */);
    }
}
