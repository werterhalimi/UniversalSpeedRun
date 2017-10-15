package ch.werter.api;


import java.util.TimerTask;

public class Timer {

    private int hour, minute, second;
    private boolean isActive;
    private Action action;
    private java.util.Timer timer;

    public Timer() {
        this.timer = new java.util.Timer();
    }

    public void start() {
        this.isActive = true;
        this.timer = new java.util.Timer();
        this.getTimer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (addSecond() == 59) {
                    if (addMinute() == 59) {
                        addHour();
                        minute = 0;
                    }
                    second = 0;
                }
            }
        }, 0, 1000);
    }

    public void toggle(){
        if (!this.isActive())
            this.start();
        else
            this.pause();
    }

    public void pause() {
        if(!isActive) return;
        this.isActive = false;
        this.getTimer().cancel();
    }

    private void reset(boolean restart) {
        this.setHour(0);
        this.setMinute(0);
        this.setSecond(0);
        if (!restart) this.pause();
    }

    public void reset() {
        this.reset(false);
    }

    public void restart() {
        this.reset(true);
    }

    public int addSecond() {
        if (this.action != null) this.getAction().eachSecond();
        return ++this.second;
    }

    public int addMinute() {
        if (this.action != null) this.getAction().eachMinute();
        return ++this.minute;
    }

    public int addHour() {
        if (this.action != null) this.getAction().eachHour();
        return ++this.hour;
    }

    public String getStringFormat(){
        String second = this.getSecond() < 10 ? "0" + this.getSecond() : String.valueOf(this.getSecond());
        String minute = this.getMinute() < 10 ? "0" + this.getMinute() : String.valueOf(this.getMinute());
        String hour = this.getHour() < 10 ? "0" + this.getHour() : String.valueOf(this.getHour());
        return hour + ":" + minute + ":" + second;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public boolean isActive() {
        return isActive;
    }

    public java.util.Timer getTimer() {
        return timer;
    }

    public interface Action {
        void eachSecond();

        void eachMinute();

        void eachHour();

    }
}
