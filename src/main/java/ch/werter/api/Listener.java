package ch.werter.api;

import ch.werter.api.event.ToggleTimerEvent;

public interface Listener {

    void toggleTimerEvent(ToggleTimerEvent event);

}
