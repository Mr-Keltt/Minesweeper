package net.davidenko_dmitriy.gui;

import net.davidenko_dmitriy.constants.Constants;
import net.davidenko_dmitriy.gamecontron.GameController;
import net.davidenko_dmitriy.gameobjects.Field;
import net.davidenko_dmitriy.settings.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameGUI extends GUI {
    private GameController gameController;
    private JPanel header;
    private Сounter bombCounter;
    private JPanel buttonContainer;
    private JButton restartButton;
    private JButton switchButton;
    private Timer timer;
    private Field field;
    private FieldGUI fieldGUI;
    private ActionListener restartActionListener;
    private int interactionType;


    public GameGUI(GameController gameController, Field field) {
        super("Minesweeper");

        this.gameController = gameController;
        this.header = createHeader();
        this.field = field;
        this.fieldGUI = new FieldGUI(gameController, this, field);
        this.interactionType = 0;

        this.add(header);
        this.add(fieldGUI.getContainer());
    }

    public Сounter getBombCounter() {
        return bombCounter;
    }

    public FieldGUI getFieldGUI() {
        return fieldGUI;
    }

    public JButton createButton(String pathIcon, String pathEnabledIcon) {
        JButton button = new JButton();

        try {
            Image icon = ImageIO.read(new File(pathIcon));
            button.setIcon(new ImageIcon(icon));

            Image disabledIcon = ImageIO.read(new File(pathEnabledIcon));
            button.setDisabledIcon(new ImageIcon(disabledIcon));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        return button;
    }

    public void setActionListenerSwitchButton() {
        switchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.switchInteractionType();
            }
        });
    }

    public void switchImageSwitchButton() {
        buttonContainer.remove(switchButton);

        try {
            if (interactionType == 0) {
                Image icon = ImageIO.read(new File(Constants.SWITCH_BUTTON_MARC_IMG_PATH));
                switchButton.setIcon(new ImageIcon(icon));
                interactionType = 1;
            }
            else {
                Image icon = ImageIO.read(new File(Constants.SWITCH_BUTTON_SHOVEL_IMG_PATH));
                switchButton.setIcon(new ImageIcon(icon));
                interactionType = 0;
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        buttonContainer.add(switchButton);
    }

    public void reset() {
        this.interactionType = 0;
        this.remove(header);
        this.remove(fieldGUI.getContainer());

        setWindowSizeByCells();

        header = createHeader();
        fieldGUI.reset();

        this.add(header);
        this.add(fieldGUI.getContainer());
    }

    public void loss() {
        try {
            Image icon = ImageIO.read(new File(Constants.RESTART_BUTTON_LOSS_IMG_PATH));
            restartButton.setIcon(new ImageIcon(icon));
            interactionType = 1;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        fieldGUI.lock();
    }

    public void win() {
        try {
            Image icon = ImageIO.read(new File(Constants.RESTART_BUTTON_WIN_IMG_PATH));
            restartButton.setIcon(new ImageIcon(icon));
            interactionType = 1;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        fieldGUI.lock();
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
        bombCounter = new Сounter(bombCounterX, counterY);
        buttonContainer = createButtonContainer(buttonContainerX, buttonContainerY);
        timer = new Timer(TimerX, counterY);

        initHeaderPanel(header);

        // adding an element to a header
        header.add(bombCounter.getCounter());
        header.add(buttonContainer);
        header.add(timer.getCounter());


        return header;
    }

    private JPanel createButtonContainer(int X, int Y) {
        buttonContainer = new JPanel();
        JButton settingsButton = createButton(Constants.SETTING_BUTTON_IMG_PATH, Constants.SETTING_BUTTON_IMG_PATH);
        restartButton = createButton(Constants.RESTART_BUTTON_IMG_PATH, Constants.SETTING_BUTTON_IMG_PATH);
        switchButton = createButton(Constants.SWITCH_BUTTON_SHOVEL_IMG_PATH, Constants.SETTING_BUTTON_IMG_PATH);


        initButtonContainer(buttonContainer, X, Y);

        settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.openSettings();
            }
        });
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.restartGame();
            }
        });


        buttonContainer.add(settingsButton);
        buttonContainer.add(restartButton);
        buttonContainer.add(switchButton);

        return buttonContainer;
    }

    @Override
    protected void initWindow() {
        // Setting window parameters
        setWindowSizeByCells();
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

    private void setWindowSizeByCells() {
        int width, height;
        int horizontalSize = Settings.horizontalSize;
        int verticalSize = Settings.verticalSize;


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
