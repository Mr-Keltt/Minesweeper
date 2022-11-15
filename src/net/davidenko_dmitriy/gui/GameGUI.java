package net.davidenko_dmitriy.gui;

import net.davidenko_dmitriy.constants.Constants;

import javax.swing.*;

public class GameGUI extends JFrame {
    // Constructor
    public GameGUI() {
        super("Minesweeper");

        setSizeByCells(Constants.Beginner_Horizontal_Size, Constants.Beginner_Vertical_Size);
        this.setLocation(Constants.Window_Location_X, Constants.Window_Location_Y);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
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
        this.setSize(width, height);
    }
}
