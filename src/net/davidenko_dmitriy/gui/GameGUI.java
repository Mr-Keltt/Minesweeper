package net.davidenko_dmitriy.gui;

import net.davidenko_dmitriy.constants.Constants;
import net.davidenko_dmitriy.settings.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameGUI extends GUI {
    JPanel header;

    // Counters
    private Сounter bombCounter;
    private Timer timer;


    // Constructor
    public GameGUI() {
        super("Minesweeper");

        header = createHeader();

        this.add(header);
    }

    // Setters and getters
    public Сounter getBombCounter() {
        return bombCounter;
    }

    public Timer getTimer() {
        return timer;
    }


    public void resizeWindow(int horizontalSize, int verticalSize) {
        this.remove(header);

        setWindowSizeByCells(horizontalSize, verticalSize);

        header = createHeader();
        this.add(header);
    }



    private JPanel createHeader() {
        JPanel header = new JPanel();
        JPanel buttonContainer;

        // calculation of the coordinates of the header elements
        int counterY = (Constants.HEADER_HEIGHT - Constants.HEADER_ELEMENT_HEIGHT) / 2;
        int bombCounterX = (Constants.HEADER_HEIGHT - Constants.HEADER_ELEMENT_HEIGHT) / 2;
        int TimerX = this.getWidth() - frameWidth - (Constants.HEADER_HEIGHT - Constants.HEADER_ELEMENT_HEIGHT) / 2 - Constants.SCORE_WIDTH;
        int buttonContainerY = (Constants.HEADER_HEIGHT - (int)(Constants.HEADER_ELEMENT_HEIGHT *0.8)) / 2;
        int buttonContainerX = (this.getWidth() - frameWidth) / 2 - ((int)(Constants.HEADER_ELEMENT_HEIGHT *0.8)*3 + buttonContainerY*2) / 2;


        // create counters
        bombCounter = new Сounter(bombCounterX, counterY, Settings.bombCount);
        timer = new Timer(TimerX, counterY, 0);

        buttonContainer = createButtonContainer(buttonContainerX, buttonContainerY);
        initHeaderPanel(header);

        // adding an element to a header
        header.add(bombCounter.getCounter());
        header.add(buttonContainer);
        header.add(timer.getCounter());


        return header;
    }

    private JPanel createButtonContainer(int X, int Y) {
        JPanel buttonContainer = new JPanel();
        JButton settingsButton = createButton(Constants.SETTING_BUTTON_IMG_PATH);
        JButton restartButton = createButton(Constants.RESTART_BUTTON_IMG_PATH);
        JButton switchButton = createButton(Constants.SWITCH_BUTTON_SHOVEL_IMG_PATH);


        initButtonContainer(buttonContainer, X, Y);

        GameGUI gameGUI = this;
        settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SettingsGUI settingsGUI = new SettingsGUI(gameGUI);
            }
        });
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        switchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });


        buttonContainer.add(settingsButton);
        buttonContainer.add(restartButton);
        buttonContainer.add(switchButton);

        return buttonContainer;
    }

    private JButton createButton(String path) {
        JButton button = new JButton();

        try {
            Image icon = ImageIO.read(new File(path));
            button.setIcon(new ImageIcon(icon));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        return button;
    }

    @Override
    protected void initWindow() {
        // Setting window parameters
        setWindowSizeByCells(Constants.START_HORIZONTAL_SIZE, Constants.START_VERTICAL_SIZE);
        this.setLocation(Constants.WINDOW_LOCATION_X, Constants.WINDOW_LOCATION_Y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
    }

    private void initHeaderPanel(JPanel header) {
        // setting a parameter header
        header.setLayout(null);
        header.setSize(new Dimension(this.getSize().width, Constants.HEADER_HEIGHT));
        header.setBackground(Constants.HEADER_BACKGROUND);
    }

    private void initButtonContainer(JPanel buttonContainer, int X, int Y) {
        buttonContainer.setLocation(X,Y);
        buttonContainer.setSize((int)(Constants.HEADER_ELEMENT_HEIGHT *0.8)*3 + Y*2, (int)(Constants.HEADER_ELEMENT_HEIGHT *0.8));
        buttonContainer.setOpaque(false);
        buttonContainer.setLayout(new GridLayout(1, 0, Y, 0));
    }

    private void setWindowSizeByCells(int horizontalSize, int verticalSize) {
        // create local variables
        int width, height;

        // calculate window width
        if (Constants.CELL_EDGE_LENGTH * horizontalSize <= Constants.MIN_GAME_WINDOW_WIDTH) {
            width = Constants.MIN_GAME_WINDOW_WIDTH;
        }
        else {
            width = Constants.CELL_EDGE_LENGTH * horizontalSize;
        }

        // calculate and set window height
        height = Constants.CELL_EDGE_LENGTH * verticalSize + Constants.HEADER_HEIGHT;


        // set window size
        this.setSize(width + frameWidth, height + frameHeight);
    }
}
