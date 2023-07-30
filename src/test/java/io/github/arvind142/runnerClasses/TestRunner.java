package io.github.arvind142.runnerClasses;

import io.github.arvind142.base.TestBase;
import io.github.arvind142.framework.framework.annotation.TestInfo;
import io.qameta.allure.Flaky;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestRunner extends TestBase {

    @Test(description = "MyTest")
    @Severity(SeverityLevel.MINOR)
    public void test() {
        assertEquals("2", "3");
    }

    @Test
    @Flaky
    @TestInfo(author = "something")
    public void test2() {
        assertEquals(0, 0);
    }
}
