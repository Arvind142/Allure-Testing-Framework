package io.github.arvind142.framework.reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.github.arvind142.framework.annotation.TestInfo;
import io.github.arvind142.framework.constants.FrameworkConstants;
import io.github.arvind142.framework.constants.HTMLConstants;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class TestReporter {
    private TestReporter(){

    }
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
        setAuthor(result);
        setCategory(result);
        setDependentInformation(result);
    }

    public static void assignDevice(String device){
        extentTestThreadLocal.get().assignDevice(device);
    }

    public static void flushReporting(ITestContext iTestContext){
        reporter.flushReporting();
        extentTestThreadLocal.remove();
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
    public static void log(Status status, Markup markup){
        extentTestThreadLocal.get().log(status,markup);
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
        String annotationTestDescription=null;
        String[] annotationTestName=null;
        String testName = (iTestResult.getMethod().getQualifiedName());
        try {
            annotationTestName = iTestResult.getMethod().getConstructorOrMethod().getMethod()
                    .getAnnotation(TestInfo.class).testName();

            annotationTestDescription = iTestResult.getMethod().getConstructorOrMethod().getMethod()
                    .getAnnotation(TestInfo.class).testDescription();
            annotationTestDescription=annotationTestDescription.equals(FrameworkConstants.notApplicable)?null:annotationTestDescription;

            return HTMLConstants.ResultHtml.getTestDescription(annotationTestName,annotationTestDescription);
        }
        catch(Exception e) {
            log.warn("@TestDescription is not used with "+testName);
            return null;
        }
     }

    private static void setAuthor(ITestResult iTestResult){
        String author=null;
        String testName = iTestResult.getMethod().getQualifiedName();
        try{
            author = iTestResult.getMethod().getConstructorOrMethod().getMethod()
                    .getAnnotation(TestInfo.class).author();
            author=author.equals(FrameworkConstants.notApplicable)?null:author;
            author=author.replace(" ","&nbsp;");
            if(author!=null){
                extentTestThreadLocal.get().assignAuthor(author);
            }
        }catch(Exception e) {
            log.warn("@TestDescription is not used with "+testName);
        }
    }

    private static void setCategory(ITestResult iTestResult){
        String[] category = iTestResult.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(Test.class).groups();
        category=category.length==0?null:category;
        if(category!=null){
            extentTestThreadLocal.get().assignCategory(category);
        }
    }

    private static void setDependentInformation(ITestResult result){
        String[] dependsOnMethod = result.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(Test.class).dependsOnMethods();
        dependsOnMethod=dependsOnMethod.length==0?null:dependsOnMethod;

        String[] dependsOnGroup = result.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(Test.class).dependsOnGroups();
        dependsOnGroup=dependsOnGroup.length==0?null:dependsOnGroup;

        if(dependsOnGroup!=null || dependsOnMethod!=null){
            if(dependsOnMethod!=null){
                log(Status.INFO,MarkupHelper.createLabel("TestCase was dependent on method/s "+Arrays.toString(dependsOnMethod)+"", ExtentColor.GREEN));
            }
            if(dependsOnGroup!=null){
                log(Status.INFO,MarkupHelper.createLabel("TestCase was dependent on group/s "+Arrays.toString(dependsOnGroup)+"", ExtentColor.GREEN));
            }
        }
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
