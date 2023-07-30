package io.github.arvind142.framework.framework.factory;

import io.github.arvind142.framework.framework.constants.ConfigConstants;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.util.Properties;

@Slf4j
public class ConfigFactory implements Cloneable {
    private static ConfigFactory configFactory;
    private final Properties executionConfig;
    private final Properties browserConfig;
    private final Properties envConfig;
    Yaml yaml = new Yaml();

    private ConfigFactory() {
        log.trace("Constructor called");
        log.trace("reading executionConfig");
        executionConfig = loadConfig(ConfigConstants.executionPropertyPath);
        log.trace("reading environmentConfig");
        envConfig = loadConfig(ConfigConstants.envPropertyPath(executionConfig.getProperty("Environment")));
        log.trace("reading browserConfig");
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

    /**
     * Read property file accepts proeprty file path
     * @param path
     * @return
     */
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

    /**
     * method will override property values if any env variables is set with same key
     * @param properties
     * @return
     */
    public Properties readAndReplacePropertyValues(Properties properties){
        for(Object key:properties.keySet()){
            String systemPropertyValue=System.getProperty(String.valueOf(key));
            if(!isNullOrEmpty(systemPropertyValue)){
                log.trace("{} key has value {} will be replaced with env value {}",key,properties.get(key),systemPropertyValue);
                properties.replace(key,systemPropertyValue);
            }
        }
        return properties;
    }

    private boolean isNullOrEmpty(Object o){
        return o == null || (String.valueOf(o).trim().equalsIgnoreCase(""));
    }
}
