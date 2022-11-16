package net.davidenko_dmitriy.gui;

import net.davidenko_dmitriy.constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class GameGUI extends JFrame {
    // Window frame sizes
    private int frameWidth;
    private int frameHeight;

    private JLabel bombCounterValue;

    private JLabel timerValue;


    // Constructor
    public GameGUI() {
        // Setting the window name
        super("Minesweeper");


        // Setting the starting value of variables
        bombCounterValue = new JLabel(String.valueOf(Constants.Beginner_Bombs_Count));
        timerValue = new JLabel("00:00:00");


        // Calculating the size of the window frames
        this.setSize(200,200);
        JPanel test = new JPanel();
        this.add(test);
        setVisible(true);
        frameWidth = this.getWidth() - test.getWidth();
        frameHeight = this.getHeight() - test.getHeight();
        this.remove(test);


        // Setting window parameters
        setSizeByCells(Constants.Beginner_Horizontal_Size, Constants.Beginner_Vertical_Size);
        this.setLocation(Constants.Window_Location_X, Constants.Window_Location_Y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);


        drawingHeader();
    }


    // Set window size
    public void setSizeByCells(int horizontalSize, int verticalSize) {
        // create local variables
        int width, height;

        // calculate window width
        if (Constants.Cell_Edge_Length * horizontalSize <= Constants.Min_Game_Window_Width) {
            width = Constants.Min_Game_Window_Width;
        }
        else {
            width = Constants.Cell_Edge_Length * horizontalSize;
        }

        // calculate window height
        height = Constants.Cell_Edge_Length * verticalSize + Constants.Header_Height;


        // set window size
        this.setSize(width + frameWidth, height + frameHeight);
    }


    // creating and displaying a header
    public void drawingHeader() {
        // creature header
        JPanel header = new JPanel();

        // creating a bomb counter
        JPanel bombCounter = new JPanel();

        // creating a timer
        JPanel timer = new JPanel(new BorderLayout());


        // initializing header elements
        initHeaderPanel(header);
        initBombCounter(bombCounter);
        initTimer(timer);


        // adding a header to the screen
        this.add(header);

        // adding a bomb counter to a header
        header.add(bombCounter);

        // adding a timer to a header
        header.add(timer);
    }


    // initializing the header panel
    private void initHeaderPanel(JPanel header) {
        // setting a parameter header
        header.setSize(new Dimension(this.getSize().width, Constants.Header_Height));
        header.setBackground(Constants.Header_Background);
        header.setLayout(null);
    }

    // initializing the bomb counter
    private void initBombCounter(JPanel bombCounter) {
        // setting a parameter bomb counter
        bombCounter.setLayout(new BorderLayout());
        bombCounter.setSize(new Dimension(Constants.Score_Width, Constants.Score_Height));
        bombCounter.setLocation((Constants.Header_Height - Constants.Score_Height) / 2, (Constants.Header_Height - Constants.Score_Height)/2);
        bombCounter.setBackground(Constants.Score_Background);

        // setting text display parameters
        this.bombCounterValue.setForeground(Constants.Score_Text_Color);
        this.bombCounterValue.setFont(Constants.Score_Text_Font);
        this.bombCounterValue.setVerticalAlignment(JLabel.CENTER);
        this.bombCounterValue.setHorizontalAlignment(JLabel.CENTER);

        // adding text to the bomb counter
        bombCounter.add(bombCounterValue, BorderLayout.CENTER);
    }

    // initializing the timer
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
    }
}
