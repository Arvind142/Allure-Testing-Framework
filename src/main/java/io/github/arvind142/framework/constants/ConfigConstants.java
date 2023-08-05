package io.github.arvind142.framework.constants;

public class ConfigConstants {
    public static final String resourceFolderPath = "./src/test/resources/";
    public static final String executionPropertiesName = "Execution.properties";
    public static final String envConfigPropertiesName = "EnvConfig.properties";
    public static final String browserConfigPropertiesName = "Browser.properties";
    public static final String executionPropertyPath = resourceFolderPath + executionPropertiesName;
    public static final String browserPropertyPath = resourceFolderPath + browserConfigPropertiesName;

    private ConfigConstants() {
    }

    public static final String envResourceFolderPath(String envName) {
        return resourceFolderPath + envName + "/";
    }

    public static final String envPropertyPath(String envName) {
        return resourceFolderPath + envName + "/" + envConfigPropertiesName;
    }
}
