package ch.werter;

import java.awt.*;
import java.util.Properties;

public class Configuration {
    private Properties properties;

    public Configuration(Properties properties) {
        this.properties = properties;
    }

    public String getProperty(String key){
        return this.properties == null || this.properties.getProperty(key) == null ? DefaultConfig.valueOf(key.toUpperCase()).getValue() : this.properties.getProperty(key);
    }

    public void setProperty(String key, String value){
        this.properties.setProperty(key,value);
    }

    public Properties getProperties() {
        return properties;
    }

    public static enum Value {

        BACKGROUND(UniversalSpeedRun.get().getProperties().getProperty("background")),
        KEYCODE(UniversalSpeedRun.get().getProperties().getProperty("keyCode")),
        PATH(UniversalSpeedRun.get().getProperties().getProperty("path"));

        private String value;

        Value(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
