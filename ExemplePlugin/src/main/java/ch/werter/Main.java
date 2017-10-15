package ch.werter;

import ch.werter.api.Listener;
import ch.werter.api.Manager;
import ch.werter.api.Plugin;
import ch.werter.api.event.ToggleTimerEvent;

public class Main extends Plugin implements Listener{

    public Main(UClassLoader loader) {
        super(loader);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        Manager manager = UniversalSpeedRun.get().getManager();
        manager.registerEvent(this);
    }

    @Override
    public void toggleTimerEvent(ToggleTimerEvent event) {
        event.setCancelled(true);
        System.out.println(true);
    }
}
