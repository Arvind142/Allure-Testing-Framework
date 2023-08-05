package io.github.arvind142.framework.listeners;

import io.github.arvind142.framework.constants.ReportingConstants;
import org.testng.ISuite;
import org.testng.internal.Utils;
import org.testng.reporters.XMLReporter;
import org.testng.reporters.XMLReporterConfig;
import org.testng.reporters.XMLStringBuffer;
import org.testng.xml.XmlSuite;
import java.util.List;
import java.util.Properties;

/**
 * Java class is copy-pasted as we need testng-results.xml in test-output to different location,
 * @see XMLTestReporter#generateReport(List, List, String)#Line38
 */
public class XMLTestReporter extends XMLReporter {

    @Override
    public void generateReport(
            List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        getConfig().setOutputDirectory(ReportingConstants.RESULT_OUTPUT_FOLDER);
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
