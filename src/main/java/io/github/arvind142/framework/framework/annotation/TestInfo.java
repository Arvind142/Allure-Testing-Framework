package io.github.arvind142.framework.framework.annotation;

public @interface TestInfo {

    String author() default "";

    String[] category() default {};

    String[] testName() default {};

    String testDescription() default "";
}
