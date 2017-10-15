package ch.werter.api;

import ch.werter.UClassLoader;

public abstract class Plugin {

    private UClassLoader loader;

    public Plugin(UClassLoader loader) {
        this.loader = loader;
    }

    public void onEnable(){}

    public void onDisable(){}

    protected UClassLoader getLoader(){
        return this.loader;
    }
}
