package ch.werter.gui.listener;

import ch.werter.gui.Main;

import java.awt.event.ActionEvent;

public class ActionListener implements java.awt.event.ActionListener {

    private Main frame;

    public ActionListener(Main frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.frame.getColorGui().setVisible(!this.frame.getColorGui().isVisible());
    }
}
