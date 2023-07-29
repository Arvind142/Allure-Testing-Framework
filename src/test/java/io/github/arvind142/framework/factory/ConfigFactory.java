package io.github.arvind142.framework.factory;

import io.github.arvind142.framework.constants.FrameworkConstants;
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
        executionConfig = loadConfig(FrameworkConstants.executionPropertyPath);
        envConfig = loadConfig(FrameworkConstants.envPropertyPath(executionConfig.getProperty("Environment")));
        browserConfig = loadConfig(FrameworkConstants.browserPropertyPath);
    }

    public static void init() {
        if (configFactory == null) {
            synchronized (ConfigFactory.class) {
                configFactory = new ConfigFactory();
            }
        }
    }

    public static Properties getExecutionProperties() {
        return (Properties) configFactory.executionConfig.clone();
    }

    public static Properties getEnvironmentProperties() {
        return (Properties) configFactory.envConfig.clone();
    }

    public static Properties getBrowserProperties() {
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
