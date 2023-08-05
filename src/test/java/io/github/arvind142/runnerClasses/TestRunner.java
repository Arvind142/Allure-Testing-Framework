package io.github.arvind142.runnerClasses;

import io.github.arvind142.base.TestBase;
import io.github.arvind142.framework.annotation.TestInfo;
import io.github.arvind142.framework.builder.DriverBuilder;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestRunner extends TestBase {

    @DataProvider(name = "dp")
    public Object[][] dp(){
        return new Object[][]{
                {"1","1"},
                {"2","0"},
                {"3","3"}
        };
    }

    @Test(dataProvider = "dp")
    public void test(String a,String b) {
        assertEquals(a, b);
    }

    @Test(invocationCount = 2)
    @TestInfo(author = "Arvind Choudhary", testName = {"Test2","TEst3"})
    public void test2() throws AssertionError{
        assertTrue(true);
    }


    @Test
    @TestInfo(author = "Arvind Choudhary", testName = "Test3",testDescription = "abc")
    public void test3() {
        DriverBuilder.init();
        throw new SkipException("Skip");
    }
}
