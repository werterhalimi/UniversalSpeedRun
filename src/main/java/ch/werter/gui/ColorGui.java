package ch.werter.gui;


import ch.werter.UniversalSpeedRun;
import ch.werter.api.utils.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColorGui extends JFrame {

    private JPanel panel;
    private Main main;

    public ColorGui(Main frame){
        this.setSize(400,125);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.main = frame;
        this.panel = new JPanel();
        List<Field> fieldList = new ArrayList<>(Arrays.asList(Color.class.getFields()));
        fieldList.removeIf(field -> StringUtils.containsLowerChar(field.getName()));
        for(Field field : fieldList)
            try {
                Object object = field.get(Color.class);
                if(object instanceof Color) {
                    Color color = (Color) object;
                    JButton button = new JButton(field.getName());
                    button.setBackground(color);
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            UniversalSpeedRun.get().getProperties().setProperty("background",String.valueOf(color.getRGB()));
                            UniversalSpeedRun.get().saveConfig();
                            main.getContentPane().setBackground(color);
                        }
                    });
                    this.panel.add(button);
                }
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
        JTextField textField = new JTextField("000000");
        textField.setSize(200,200);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    main.getContentPane().setBackground(new Color(Integer.parseInt( textField.getText(),16)));

                }catch (NumberFormatException e){
                    textField.setText("000000");
                }
            }
        });
        this.panel.add(textField);
        this.setContentPane(this.panel);
    }
}
