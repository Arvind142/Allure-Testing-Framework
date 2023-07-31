package io.github.arvind142.framework.framework.annotation;

import io.github.arvind142.framework.framework.constants.FrameworkConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestInfo {

    String author() default FrameworkConstants.notApplicable;

    String[] category() default {};

    String[] testName() default {};

    String testDescription() default FrameworkConstants.notApplicable;
}
