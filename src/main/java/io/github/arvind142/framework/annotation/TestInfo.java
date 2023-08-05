package io.github.arvind142.framework.annotation;

import io.github.arvind142.framework.constants.FrameworkConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestInfo {

    String author();

    String[] testName();

    String testDescription() default FrameworkConstants.NOT_APPLICABLE;
}
