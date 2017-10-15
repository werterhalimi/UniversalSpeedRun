package ch.werter;

import ch.werter.api.Manager;
import ch.werter.api.Timer;
import ch.werter.api.event.Event;
import ch.werter.api.event.ToggleTimerEvent;
import ch.werter.gui.Main;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.io.*;
import java.util.Properties;
import java.util.logging.LogManager;

public class UniversalSpeedRun {

    private static UniversalSpeedRun instance;
    private Configuration configuration;
    private Properties properties;
    private Main mainGui;
    private Timer timer;
    private int keyCode;
    private Manager manager;
    private File config;

    public static void main(String[] args) {
        instance = new UniversalSpeedRun();
        get().configuration = new Configuration(new Properties());
        get().properties = get().configuration.getProperties();
        File folder = new File(get().getProperties().getProperty("path"));
        if(!folder.exists()) folder.mkdirs();
        get().config = new File(folder.getAbsolutePath()+"/config.properties");
        InputStream input = null;
        try {
            if(!get().config.exists()) get().saveDefaultConfig();
            input = new FileInputStream(get().config);
            get().properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            GlobalScreen.registerNativeHook();
            LogManager.getLogManager().reset();
        }
        catch (NativeHookException e) {
            e.printStackTrace();
            System.exit(1);
        }finally {
            GlobalScreen.addNativeKeyListener(new KeyListener());
        }

        get().mainGui = new Main("Chrono",new Dimension(400,120));
        get().mainGui.init();
        get().manager = new Manager();
        get().timer  = get().mainGui.getTimer();
        get().update();
        get().loadPlugin();
    }

    public static UniversalSpeedRun get() {
        return instance;
    }

    public Main getMainGui() {
        return mainGui;
    }

    private void loadPlugin() {
        File plugins = new File(Configuration.Value.PATH.getValue() + "/plugins");
        if (!plugins.exists()) {
            plugins.mkdirs();
            return;
        }
        for (File file : plugins.listFiles()) {
            try {
                new UClassLoader(file,this.getClass().getClassLoader(),get().getManager());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void update(){
        keyCode = Integer.parseInt(properties.getProperty("keyCode"));
        mainGui.update();
    }

    public void saveDefaultConfig(){
        try {
            config.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        for (DefaultConfig config : DefaultConfig.values()) {
            properties.setProperty(config.getKey(),config.getValue());
        }
        saveConfig();
        update();
    }

    public void saveConfig(){
        OutputStream out = null;
        try{
            out = new FileOutputStream(config);
            properties.store(out,"If UniversalSpeedRun doesn't work try to delete this file or remove plugin !");
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Configuration getProperties() {
        return configuration;
    }

    public Timer getTimer() {
        return timer;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public Manager getManager(){
        return manager;
    }


    private static void callEvent(Event event){
        if (get().manager.getRegisteredEvents().isEmpty()){
            event.execute(null);
            return;
        }
        get().manager.getRegisteredEvents().forEach(listener -> event.execute(listener));
    }

    public static class KeyListener implements NativeKeyListener {

        public void nativeKeyTyped(NativeKeyEvent event) {
            return;
        }

        public void nativeKeyPressed(NativeKeyEvent event) {
            if (event.getKeyCode() == UniversalSpeedRun.get().getKeyCode()){
                callEvent(new ToggleTimerEvent());
            }
        }

        public void nativeKeyReleased(NativeKeyEvent event) {
            return;
        }
    }

}

