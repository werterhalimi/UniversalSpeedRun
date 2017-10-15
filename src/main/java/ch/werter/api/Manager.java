package ch.werter.api;

import ch.werter.api.event.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Manager {

    private List<Listener> registeredEvents;
    private List<Plugin> plugins;

    public Manager(){
        this.registeredEvents = new ArrayList<Listener>();
        this.plugins = new ArrayList<>();
    }

    public void disablePlugin(Plugin plugin){
        plugin.onDisable();
        try {
            plugin.getLoader().close();
        }catch (IOException e){
            e.printStackTrace();
        }
        this.plugins.remove(plugin);
    }

    public void enablePlugin(Plugin plugin){
        plugin.onEnable();
        this.plugins.add(plugin);
    }

    public void callEvent(Event event){
        this.registeredEvents.forEach(listener -> event.execute(listener));
    }

    public void registerEvent(Listener listener){
        this.registeredEvents.add(listener);
    }

    public List<Listener> getRegisteredEvents() {
        return registeredEvents;
    }
}
