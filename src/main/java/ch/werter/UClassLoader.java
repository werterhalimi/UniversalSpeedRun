package ch.werter;


import ch.werter.api.Manager;
import ch.werter.api.Plugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;


public class UClassLoader extends URLClassLoader{

    private File file;

    public UClassLoader(File file, ClassLoader classLoader, Manager manager) throws Exception{
        super(new URL[] {file.toURI().toURL()}, classLoader);
        this.file = file;

        if(this.getResource("plugin.properties") == null) {
            System.out.println("There is no plugin.properties");
            return;
        }

        Properties properties = new Properties();
        properties.load(this.getResourceAsStream("plugin.properties"));

        Class<?> clazz = Class.forName(properties.getProperty("main"), true , this);
        Plugin plugin = clazz.asSubclass(Plugin.class).getDeclaredConstructor(UClassLoader.class).newInstance(this);
        manager.enablePlugin(plugin);
    }

    public File getFile() {
        return file;
    }
}
