package net.davidenko_dmitriy.gui;

import net.davidenko_dmitriy.gui.Сounter;

import javax.swing.*;

public class Timer extends Сounter {
    int hours;
    int minutes;
    int seconds;

    public Timer(int X, int Y) {
        super(X, Y);
        setValue(0);
    }

    @Override
    public void setValue(int sec) {
        if (sec > 0) {
            hours = sec/360;
            minutes = (sec - hours*360) / 60;
            seconds = sec - minutes*60 - hours*360;
        }
        else {
            hours = 0;
            minutes = 0;
            seconds = 0;
        }

        value.setText(getTimeString());
    }

    @Override
    public void increase() {
        if (hours*360 + minutes*60 + seconds < 99*360 + 59*60 + 59*60) {
            setValue(hours * 360 + minutes * 60 + seconds + 1);
        }
        else {
            setValue(0);
        }
    }

    @Override
    public void reduce() {
        if (hours*360 + minutes*60 + seconds > 0) {
            setValue(hours * 360 + minutes * 60 + seconds - 1);
        }
        else {
            setValue(0);
        }
    }


    private String getTimeString() {
        String sHours = String.valueOf(hours);
        String sMinutes = String.valueOf(minutes);
        String sSeconds = String.valueOf(seconds);

        if (sHours.length() == 1) {
            sHours = "0" + sHours;
        }

        if (sMinutes.length() == 1) {
            sMinutes = "0" + sMinutes;
        }

        if (sSeconds.length() == 1) {
            sSeconds = "0" + sSeconds;
        }

        return sHours + ":" + sMinutes + ":" + sSeconds;
    }
}
