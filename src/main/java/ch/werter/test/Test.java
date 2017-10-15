package ch.werter.test;

import ch.werter.UClassLoader;
import ch.werter.api.Listener;
import ch.werter.api.Plugin;
import ch.werter.api.event.ToggleTimerEvent;

public class Test extends Plugin implements Listener {

    public Test(UClassLoader loader) {
        super(loader);
    }

    @Override
    public void onEnable() {
        System.out.println();
    }

    @Override
    public void toggleTimerEvent(ToggleTimerEvent event) {
        event.setCancelled(true);
    }
}
