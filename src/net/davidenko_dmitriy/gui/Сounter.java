package net.davidenko_dmitriy.gui;

import net.davidenko_dmitriy.constants.Constants;

import javax.swing.*;
import java.awt.*;

public class Сounter {
    private JPanel counter;
    private JLabel value;
    private Point location;



    public Сounter(int X, int Y, int startVal) {
        counter = new JPanel();
        value = new JLabel();

        setLocation(X, Y);
        setValue(startVal);
        initСounter();
    }


    public JPanel getCounter() {
        return counter;
    }

    public void setValue(int val) {
        // val is within the acceptable range
        if (val >= -999999 && val <= 999999) {
            value.setText(String.valueOf(val));
        }
    }

    public void setLocation(int X, int Y) {
        location = new Point(X, Y);
    }

    public Point getLocation() {
        return location;
    }


    private void initСounter() {
        // setting a parameter counter
        counter.setLayout(new BorderLayout());
        counter.setSize(new Dimension(Constants.Score_Width, Constants.Score_Height));
        counter.setLocation(location.x, location.y);
        counter.setBackground(Constants.Score_Background);

        // setting text display parameters
        value.setForeground(Constants.Score_Text_Color);
        value.setFont(Constants.Score_Text_Font);
        value.setVerticalAlignment(JLabel.CENTER);
        value.setHorizontalAlignment(JLabel.CENTER);

        // adding text to the counter
        counter.add(value, BorderLayout.CENTER);
    }
}
