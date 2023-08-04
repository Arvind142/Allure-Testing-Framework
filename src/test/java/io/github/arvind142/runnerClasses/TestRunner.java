package io.github.arvind142.runnerClasses;

import io.github.arvind142.base.TestBase;
import io.github.arvind142.framework.framework.annotation.TestInfo;
import io.github.arvind142.framework.framework.builder.DriverBuilder;
import io.qameta.allure.Flaky;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.SkipException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestRunner extends TestBase {

    @Test(description = "MyTest")
    @Severity(SeverityLevel.MINOR)
    public void test() {
        DriverBuilder.init();
        assertEquals("2", "3");
    }

    @Test
    @Flaky
    @TestInfo(author = "Arvind Choudhary", testName = {"Test2","TEst3"})
    public void test2() {
        DriverBuilder.init();
        assertEquals(0, 0);
    }


    @Test
    @Flaky
    @TestInfo(author = "Arvind Choudhary", testName = "Test3",testDescription = "abc")
    public void test3() {
        DriverBuilder.init();
        throw new SkipException("Skip");
    }
}
