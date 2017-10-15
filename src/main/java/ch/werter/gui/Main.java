package ch.werter.gui;

import ch.werter.UniversalSpeedRun;
import ch.werter.api.Timer;
import ch.werter.gui.listener.DefaultAction;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;

public class Main extends JFrame {

    private JFrame colorGui;
    private JMenu edit,configuration;
    private String name;
    private Dimension dimension;
    private Color color;
    private JPanel panel;
    private ch.werter.api.Timer timer;
    private JMenuBar bar;
    private JLabel label;

    public Main(String name, Dimension dimension)  {
        this.name = name;
        this.dimension = dimension;
        this.color = Color.decode(UniversalSpeedRun.get().getProperties().getProperty("background"));
        this.panel = new JPanel();
        this.timer = new Timer();
        this.bar = new JMenuBar();
        this.colorGui = new ColorGui(this);
    }

    public void init(){
        this.setTitle(this.getName());
        this.setSize(this.getDimension());
        this.getPanel().setBackground(this.getColor());
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.configuration = new JMenu("Configuration");
        JMenuItem reset = new JMenuItem("Reset configuration");
        reset.addActionListener(actionEvent -> {UniversalSpeedRun.get().saveDefaultConfig();});
        configuration.add(reset);

        this.edit = new JMenu("Edit");
        JMenuItem color = new JMenuItem("Color");
        color.addActionListener(new ch.werter.gui.listener.ActionListener(this));
        edit.add(color);

        this.bar.add(edit);
        this.bar.add(configuration);

        this.setJMenuBar(this.bar);
        this.label = new JLabel("00:00:00");
        this.label.setFont(new Font("Arial",100,80));
        this.panel.add(this.label);
        this.timer.setAction(new DefaultAction());
        this.setContentPane(this.getPanel());
    }

    public void update(){
        this.getContentPane().setBackground(Color.decode(UniversalSpeedRun.get().getProperties().getProperty("background")));
        this.label.setText(this.getTimer().getHour() + ":" + this.getTimer().getMinute() + ":" + this.getTimer().getSecond());
        this.label.setText(this.getTimer().getStringFormat());
    }

    public JPanel getPanel() {
        return panel;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public JFrame getColorGui() {
        return colorGui;
    }

    public void setColorGui(JFrame colorGui) {
        this.colorGui = colorGui;
    }
}
