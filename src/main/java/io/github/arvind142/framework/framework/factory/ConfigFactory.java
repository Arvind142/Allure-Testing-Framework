package io.github.arvind142.framework.framework.factory;

import io.github.arvind142.framework.framework.constants.ConfigConstants;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigFactory implements Cloneable {
    private static ConfigFactory configFactory;
    private final Properties executionConfig;
    private final Properties browserConfig;
    private final Properties envConfig;
    Yaml yaml = new Yaml();

    private ConfigFactory() {
        executionConfig = loadConfig(ConfigConstants.executionPropertyPath);
        envConfig = loadConfig(ConfigConstants.envPropertyPath(executionConfig.getProperty("Environment")));
        browserConfig = loadConfig(ConfigConstants.browserPropertyPath);
    }

    private static void init() {
        if (configFactory == null) {
            synchronized (ConfigFactory.class) {
                configFactory = new ConfigFactory();
            }
        }
    }

    public static Properties getExecutionProperties() {
        init();
        return (Properties) configFactory.executionConfig.clone();
    }

    public static Properties getEnvironmentProperties() {
        init();
        return (Properties) configFactory.envConfig.clone();
    }

    public static Properties getBrowserProperties() {
        init();
        return (Properties) configFactory.browserConfig.clone();
    }

    public Properties loadConfig(String path) {
        Properties properties = new Properties();
        try {
            System.out.println("trying to load " + path);
            properties.load(new FileInputStream(path));
        } catch (Exception e) {
            properties = new Properties();
            e.printStackTrace();
        }
        return properties;
    }
}
