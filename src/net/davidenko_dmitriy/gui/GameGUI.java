package net.davidenko_dmitriy.gui;

import net.davidenko_dmitriy.constants.Constants;
import net.davidenko_dmitriy.gui.Сounter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class GameGUI extends JFrame {
    // Window frame sizes
    private int frameWidth;
    private int frameHeight;

    // text in counters

    private Сounter bombCounter;
    private JLabel timerValue;


    // Constructor
    public GameGUI() {
        // Setting the window name
        super("Minesweeper");


        // Init text containers
        timerValue = new JLabel();


        // Calculating the size of the window frames
        this.setSize(200,200);
        JPanel test = new JPanel();
        this.add(test);
        setVisible(true);
        frameWidth = this.getWidth() - test.getWidth();
        frameHeight = this.getHeight() - test.getHeight();
        this.remove(test);


        // Setting window parameters
        setWindowSizeByCells(Constants.Beginner_Horizontal_Size, Constants.Beginner_Vertical_Size);
        this.setLocation(Constants.Window_Location_X, Constants.Window_Location_Y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);


        drawingHeader();
    }


    // Setters and getters
    public void setBombCounterValue(int bombCount) {
        if (Constants.Max_Horizontal_Size * Constants.Max_Horizontal_Size - 1 < bombCount) {
            bombCounter.setValue(bombCount);
        }
    }

    public void setTimerValueZero() {
        timerValue.setText("00:00:00");
    }

    public void setWindowSizeByCells(int horizontalSize, int verticalSize) {
        // create local variables
        int width, height;

        // calculate window width
        if (Constants.Cell_Edge_Length * horizontalSize <= Constants.Min_Game_Window_Width) {
            width = Constants.Min_Game_Window_Width;
        }
        else {
            width = Constants.Cell_Edge_Length * horizontalSize;
        }

        // calculate and set window height
        height = Constants.Cell_Edge_Length * verticalSize + Constants.Header_Height;


        // set window size
        this.setSize(width + frameWidth, height + frameHeight);
    }


    // creating and displaying a header
    public void drawingHeader() {
        // creature header
        JPanel header = new JPanel();

        // creating a bomb counter
        bombCounter = new Сounter((Constants.Header_Height - Constants.Score_Height) / 2,
                (Constants.Header_Height - Constants.Score_Height) / 2,
                Constants.Beginner_Bombs_Count);

        // creating a timer
        JPanel timer = new JPanel(new BorderLayout());


        // initializing header elements
        initHeaderPanel(header);
        initTimer(timer);


        // adding a header to the screen
        this.add(header);

        // adding a bomb counter to a header
        header.add(bombCounter.getCounter());

        // adding a timer to a header
        header.add(timer);
    }


    private void initHeaderPanel(JPanel header) {
        // setting a parameter header
        header.setSize(new Dimension(this.getSize().width, Constants.Header_Height));
        header.setBackground(Constants.Header_Background);
        header.setLayout(null);
    }

    private void initTimer(JPanel timer) {
        // setting a parameter timer
        timer.setSize(new Dimension(Constants.Score_Width, Constants.Score_Height));
        timer.setLocation(this.getWidth() - frameWidth - (Constants.Header_Height - Constants.Score_Height) / 2 - Constants.Score_Width,
                (Constants.Header_Height - Constants.Score_Height)/2);
        timer.setBackground(Constants.Score_Background);

        // setting text display parameters
        this.timerValue.setForeground(Constants.Score_Text_Color);
        this.timerValue.setFont(Constants.Score_Text_Font);
        this.timerValue.setVerticalAlignment(JLabel.CENTER);
        this.timerValue.setHorizontalAlignment(JLabel.CENTER);

        // adding text to the timer
        timer.add(timerValue, BorderLayout.CENTER);

        // set start value
        setTimerValueZero();
    }
}
