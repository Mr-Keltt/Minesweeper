package net.davidenko_dmitriy.gui;

import javax.swing.*;

public class GUI extends JFrame {
    // Window frame sizes
    protected int frameWidth;
    protected int frameHeight;


    public GUI(String frameTitle) {
        super(frameTitle);

        calculateSizeOfWindowFrames();
        initWindow();
    }


    protected void initWindow() {
        return;
    }

    private void calculateSizeOfWindowFrames() {
        // creating a time window with constant dimensions
        this.setSize(200,200);

        //creating a panel that fills the time window
        JPanel test = new JPanel();
        this.add(test);

        setVisible(true);

        // we calculate the size of the window frames
        frameWidth = this.getWidth() - test.getWidth();
        frameHeight = this.getHeight() - test.getHeight();

        this.remove(test);
    }
}
