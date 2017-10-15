package ch.werter.api.event;

import ch.werter.UniversalSpeedRun;
import ch.werter.api.Listener;

public class ToggleTimerEvent implements Event {

    private boolean cancelled;


    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void execute(Listener listener){
        if (listener != null) listener.toggleTimerEvent(this);
        if (!isCancelled()) UniversalSpeedRun.get().getTimer().toggle();
    }

}
