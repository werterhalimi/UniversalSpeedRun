package ch.werter;

import java.awt.*;

public enum DefaultConfig {
    BACKGROUND("background",String.valueOf(Color.GREEN.getRGB())),
    KEYCODE("keyCode","38"),
    PATH("path",System.getProperty("user.home")+"/USpeedRun");

    private String key;
    private String value;

    DefaultConfig(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
