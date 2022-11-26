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
        int counterY = (Constants.Header_Height - Constants.Header_Element_Height) / 2;
        int bombCounterX = (Constants.Header_Height - Constants.Header_Element_Height) / 2;
        int TimerX = this.getWidth() - frameWidth - (Constants.Header_Height - Constants.Header_Element_Height) / 2 - Constants.Score_Width;
        int buttonContainerY = (Constants.Header_Height - (int)(Constants.Header_Element_Height*0.8)) / 2;
        int buttonContainerX = (this.getWidth() - frameWidth) / 2 - ((int)(Constants.Header_Element_Height*0.8)*3 + buttonContainerY*2) / 2;


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
        JButton settingsButton = createButton("src/net/davidenko_dmitriy/resources/img/setting_button.png");
        JButton restartButton = createButton("src/net/davidenko_dmitriy/resources/img/setting_button.png");
        JButton switchButton = createButton("src/net/davidenko_dmitriy/resources/img/setting_button.png");


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
        setWindowSizeByCells(Constants.Beginner_Horizontal_Size, Constants.Beginner_Vertical_Size);
        this.setLocation(Constants.Window_Location_X, Constants.Window_Location_Y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
    }

    private void initHeaderPanel(JPanel header) {
        // setting a parameter header
        header.setLayout(null);
        header.setSize(new Dimension(this.getSize().width, Constants.Header_Height));
        header.setBackground(Constants.Header_Background);
    }

    private void initButtonContainer(JPanel buttonContainer, int X, int Y) {
        buttonContainer.setLocation(X,Y);
        buttonContainer.setSize((int)(Constants.Header_Element_Height*0.8)*3 + Y*2, (int)(Constants.Header_Element_Height*0.8));
        buttonContainer.setOpaque(false);
        buttonContainer.setLayout(new GridLayout(1, 0, Y, 0));
    }

    private void setWindowSizeByCells(int horizontalSize, int verticalSize) {
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
}
