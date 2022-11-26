package net.davidenko_dmitriy.gui;

import net.davidenko_dmitriy.constants.Constants;
import net.davidenko_dmitriy.settings.Settings;

import javax.swing.*;
import java.awt.*;

public class Сounter {
    protected JPanel counter;
    protected JLabel value;



    public Сounter(int X, int Y) {
        counter = new JPanel();
        value = new JLabel();

        setValue(Settings.bombCount);
        init(X, Y);
    }

    public JPanel getCounter() {
        return counter;
    }

    public void setValue(int val) {
        value.setText(String.valueOf(val));
    }

    public void increase() {
        setValue(Integer.parseInt(value.getText()) + 1);
    }

    public void reduce() {
        setValue(Integer.parseInt(value.getText()) - 1);
    }


    private void init(int X, int Y) {
        // setting a parameter counter
        counter.setLayout(new BorderLayout());
        counter.setSize(new Dimension(Constants.SCORE_WIDTH, Constants.HEADER_ELEMENT_HEIGHT));
        counter.setLocation(X, Y);
        counter.setBackground(Constants.SCORE_BACKGROUND);

        // setting text display parameters
        value.setForeground(Constants.SCORE_TEXT_COLOR);
        value.setFont(Constants.SCORE_TEXT_FONT);
        value.setVerticalAlignment(JLabel.CENTER);
        value.setHorizontalAlignment(JLabel.CENTER);

        // adding text to the counter
        counter.add(value, BorderLayout.CENTER);
    }
}
