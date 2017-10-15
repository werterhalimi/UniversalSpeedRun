package ch.werter;

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

    public enum Value {

        BACKGROUND("background",UniversalSpeedRun.get().getProperties().getProperty("background")),
        KEYCODE("keyCode",UniversalSpeedRun.get().getProperties().getProperty("keyCode")),
        PATH("path",UniversalSpeedRun.get().getProperties().getProperty("path"));

        private String value;
        private String key;

        Value(String value, String key) {
            this.value = value;
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
}
