package ch.werter.api.event;

import ch.werter.api.Listener;

public interface Event {

    public boolean isCancelled();

    public void setCancelled(boolean cancel);

    public void execute(Listener listener);
}
