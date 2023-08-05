package io.github.arvind142.framework.constants;

public class ConfigConstants {
    public static final String RESOURCE_FOLDER_PATH = "./src/test/resources/";
    public static final String EXECUTION_PROPERTIES_NAME = "Execution.properties";
    public static final String ENV_CONFIG_PROPERTIES_NAME = "EnvConfig.properties";
    public static final String BROWSER_CONFIG_PROPERTIES_NAME = "Browser.properties";
    public static final String EXECUTION_PROPERTY_PATH = RESOURCE_FOLDER_PATH + EXECUTION_PROPERTIES_NAME;
    public static final String BROWSER_PROPERTY_PATH = RESOURCE_FOLDER_PATH + BROWSER_CONFIG_PROPERTIES_NAME;

    private ConfigConstants() {
    }

    public static final String envResourceFolderPath(String envName) {
        return RESOURCE_FOLDER_PATH + envName + "/";
    }

    public static final String envPropertyPath(String envName) {
        return RESOURCE_FOLDER_PATH + envName + "/" + ENV_CONFIG_PROPERTIES_NAME;
    }
}
