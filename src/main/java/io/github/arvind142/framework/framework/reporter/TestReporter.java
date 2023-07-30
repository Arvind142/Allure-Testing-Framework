package io.github.arvind142.framework.framework.reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestReporter {
    private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    private static Reporter reporter;


    private static Map<String,Integer> listOfTCs = new ConcurrentHashMap<>();

    public static void init(ITestContext iTestContext){
        reporter = Reporter.init();
        setTriggerDetails(iTestContext);
    }

    public static void createTest(ITestResult result){
        extentTestThreadLocal
                .set(
                        reporter.getExtentReports().createTest(
                                getTestName(result),
                                getTestDescription(result)
                        )
                );
    }

    public static void flushReporting(ITestContext iTestContext){
        reporter.flushReporting();
    }

    /*
    ################# Logging methods #################
     */
    public static void logError(Throwable throwable){
        extentTestThreadLocal.get().log(Status.FAIL,throwable);
    }

    public static void log(Status status,String message){
        extentTestThreadLocal.get().log(status,message);
    }

    public static void logCode(Status status,String message){
        extentTestThreadLocal.get().log(status, MarkupHelper.createCodeBlock(message, CodeLanguage.JSON));
    }


    public static void logXML(Status status,String message){
        extentTestThreadLocal.get().log(status, MarkupHelper.createCodeBlock(message, CodeLanguage.XML));
    }

    /*
    ################# helper methods #################
     */

    private static String getTestName(ITestResult iTestResult){
        String[] resultDataArray = iTestResult.getMethod().getQualifiedName().split("[.]");
        String testName = resultDataArray[resultDataArray.length - 2] + "." + resultDataArray[resultDataArray.length - 1];

        // get parameters if any :)
        List<String> paramString = getParameter(iTestResult);
        if(!paramString.isEmpty()){
            // returning test name
            testName=(testName + " - [ " + paramString.stream().toArray()[0]+" ]");
        }

        // is test invoked again?
        if(listOfTCs.containsKey(testName)){
            listOfTCs.replace(testName,listOfTCs.get(testName)+1);
            testName = testName+" ( Invocation: "+listOfTCs.get(testName)+")";
        }
        else{
            listOfTCs.put(testName,1);
        }
        return testName;
    }
    private static List<String> getParameter(ITestResult result){
        // if no parameter then return test name
        if (result.getParameters().length == 0)
            return new ArrayList<>();

        // get all parameters
        Object[] objectList = result.getParameters();

        // new parameter list to fetch all params ( including variable length parameters)
        List<String> newObjectList = new ArrayList<>();

        // iterate over list
        for (Object currentObject : objectList) {
            // if parameter is of variable length args
            if (currentObject instanceof Object[]) {
                for (Object obj : (Object[])currentObject) {
                    newObjectList.add(String.valueOf(obj));
                }
            } else {
                newObjectList.add(String.valueOf(currentObject));
            }
        }
        return  newObjectList;
    }

    private static String getTestDescription(ITestResult iTestResult){
        return "";
    }

    private static String getAuthor(ITestResult iTestResult){
        return "";
    }

    private static String[] getCategory(ITestResult iTestResult){
        return new String[]{""};
    }

    private static synchronized void setTriggerDetails(ITestContext context){
        // get triggered by
        String triggerName = context.getCurrentXmlTest().getSuite().getFileName();
        if(triggerName.endsWith("temp-testng-customsuite.xml")){
            String[] params = context.getCurrentXmlTest().getClasses().get(0).getName().split("[.]");
            triggerName = params[params.length-1]+".java";
        }
        else{
            String[] params = triggerName.split("\\\\");
            triggerName = params[params.length-1];
        }
        reporter.setSystemVar("Triggered by user",System.getProperty("user.name"));
        reporter.setSystemVar("Triggered using [ .xml/.java ]",triggerName);
    }
}
