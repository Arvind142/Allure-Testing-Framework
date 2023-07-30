package io.github.arvind142.framework.framework.annotation;

import io.github.arvind142.framework.framework.constants.FrameworkConstants;

public @interface TestInfo {

    String author() default FrameworkConstants.notApplicable;

    String[] category() default {};

    String[] testName() default {};

    String testDescription() default FrameworkConstants.notApplicable;
}
