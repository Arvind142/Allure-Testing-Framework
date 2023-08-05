package io.github.arvind142.base;

import io.github.arvind142.framework.listeners.TestListener;
import io.github.arvind142.framework.listeners.XMLTestReporter;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class, XMLTestReporter.class})
public class TestBase {

}
