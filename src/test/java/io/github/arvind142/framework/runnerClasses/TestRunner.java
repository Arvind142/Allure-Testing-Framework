package io.github.arvind142.framework.runnerClasses;

import io.github.arvind142.framework.base.TestBase;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestRunner extends TestBase {

    @Test(description = "MyTest")
    public void test() {
        assertEquals("2", "3");
    }
}
