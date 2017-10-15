package ch.werter.gui.listener;

import ch.werter.UniversalSpeedRun;
import ch.werter.api.Timer;

public class DefaultAction implements Timer.Action {


    public void update(){
        UniversalSpeedRun.get().getMainGui().update();
    }

    public void eachSecond() {
        this.update();
    }

    public void eachMinute() {
        this.update();
    }

    public void eachHour() {
        this.update();
    }
}
