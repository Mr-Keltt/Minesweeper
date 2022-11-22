package net.davidenko_dmitriy.gui;

import net.davidenko_dmitriy.constants.Constants;

import javax.swing.*;
import java.awt.*;

public class Сounter {
    protected JPanel counter;
    protected JLabel value;



    public Сounter(int X, int Y, int startVal) {
        counter = new JPanel();
        value = new JLabel();

        setLocation(X, Y);
        setValue(startVal);
        init(X, Y);
    }


    public JPanel getCounter() {
        return counter;
    }

    public void setValue(int val) {
        // val is within the acceptable range
        if (val >= -(Constants.Max_Horizontal_Size * Constants.Max_Horizontal_Size - 1) &&
            val <= Constants.Max_Horizontal_Size * Constants.Max_Horizontal_Size - 1) {
            value.setText(String.valueOf(val));
        }
    }

    public void setLocation(int X, int Y) {
        counter.setLocation(X, Y);
    }

    public Point getLocation() {
        return counter.getLocation();
    }


    private void init(int X, int Y) {
        // setting a parameter counter
        counter.setLayout(new BorderLayout());
        counter.setSize(new Dimension(Constants.Score_Width, Constants.Header_Element_Height));
        counter.setLocation(X, Y);
        counter.setBackground(Constants.Score_Background);

        // setting text display parameters
        value.setForeground(Constants.Score_Text_Color);
        value.setFont(Constants.Score_Text_Font);
        value.setVerticalAlignment(JLabel.CENTER);
        value.setHorizontalAlignment(JLabel.CENTER);

        // adding text to the counter
        counter.add(value, BorderLayout.CENTER);
    }

    public void increase() {
        setValue(Integer.parseInt(value.getText()) + 1);
    }

    public void reduce() {
        setValue(Integer.parseInt(value.getText()) - 1);
    }
}
