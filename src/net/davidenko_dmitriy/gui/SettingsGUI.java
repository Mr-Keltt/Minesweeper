package net.davidenko_dmitriy.gui;

import net.davidenko_dmitriy.constants.Constants;
import net.davidenko_dmitriy.gamecontron.GameController;
import net.davidenko_dmitriy.settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsGUI extends GUI {
    private GameController gameController;
    private GameGUI gameGUI;
    private JTextField horizontalSizeText;
    private JTextField verticalSizeTest;
    private JTextField bombCountText;


    public SettingsGUI(GameController gameController, GameGUI gameGUI) {
        super("Settings Minesweeper");

        this.gameController = gameController;
        this.gameGUI = gameGUI;

        JPanel inputContainer = createInputContainer();
        JPanel buttonContainer = createButtonContainer();

        this.add(inputContainer);
        this.add(buttonContainer);

        horizontalSizeText.setText(String.valueOf(Settings.horizontalSize));
        verticalSizeTest.setText(String.valueOf(Settings.verticalSize));
        bombCountText.setText(String.valueOf(Settings.bombCount));
    }

    // Setters and getters
    public int getHorizontalSizeText() {
        return Integer.parseInt(horizontalSizeText.getText());
    }

    public int getVerticalSizeTest() {
        return Integer.parseInt(verticalSizeTest.getText());
    }

    public int getBombCountText() {
        return Integer.parseInt(bombCountText.getText());
    }

    private JPanel createInputContainer() {
        JPanel inputContainer = new JPanel();
        initInputContainer(inputContainer, 0, 0);

        JLabel horizontalSizeLabel = createLabel("Ширина");
        JLabel verticalSizeLabel = createLabel("Высота");
        JLabel bombCountLabel = createLabel("Количество бомб");
        horizontalSizeText = createTextField();
        verticalSizeTest = createTextField();
        bombCountText = createTextField();

        inputContainer.add(horizontalSizeLabel);
        inputContainer.add(horizontalSizeText);
        inputContainer.add(verticalSizeLabel);
        inputContainer.add(verticalSizeTest);
        inputContainer.add(bombCountLabel);
        inputContainer.add(bombCountText);

        return inputContainer;
    }

    private JPanel createButtonContainer() {
        JPanel buttonContainer = new JPanel();
        initButtonContainer(buttonContainer, 0, (int)((this.getHeight() - frameHeight)*0.7));

        JButton applyButton = createButton("Применить изменения");

        buttonContainer.add(applyButton);

        return buttonContainer;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(Constants.SCORE_TEXT_FONT);

        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(Constants.SETTINGS_TEXT_FONT);
        int margin = Constants.SETTINGS_MARGIN;
        textField.setBorder(BorderFactory.createEmptyBorder(0, margin, 0, margin));

        return textField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(Constants.SETTINGS_TEXT_FONT);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setCorrectValues();

                gameController.applySettings(getHorizontalSizeText(), getVerticalSizeTest(), getBombCountText());
                dispose();
            }
        });

        return button;
    }

    @Override
    protected void initWindow() {
        // Setting window parameters
        this.setSize(new Dimension(Constants.SETTINGS_WINDOW_WIDTH, Constants.SETTINGS_WINDOW_HEIGHT));
        this.setLocation(Constants.WINDOW_LOCATION_X + 100, Constants.WINDOW_LOCATION_Y + 100);
        this.setResizable(false);
        this.setLayout(null);
        int margin = Constants.SETTINGS_MARGIN;
        getRootPane().setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
    }

    private void initInputContainer(JPanel inputContainer, int X, int Y) {
        int inputContainerWidth = this.getWidth() - frameWidth - Constants.SETTINGS_MARGIN*2;
        int inputContainerHeight = (int)((this.getHeight() - frameHeight)*0.7) - Constants.SETTINGS_MARGIN;

        inputContainer.setLayout(new GridLayout(3, 2, 10, 10));
        inputContainer.setLocation(X,Y);
        inputContainer.setSize(new Dimension(inputContainerWidth, inputContainerHeight));
    }

    private void initButtonContainer(JPanel buttonContainer, int X, int Y) {
        int buttonContainerWidth = this.getWidth() - frameWidth - Constants.SETTINGS_MARGIN*2;
        int buttonContainerHeight =  (int)((this.getHeight() - frameHeight)*0.3) - Constants.SETTINGS_MARGIN*2;

        buttonContainer.setSize(new Dimension(buttonContainerWidth, buttonContainerHeight));
        buttonContainer.setLocation(X, Y);
        buttonContainer.setLayout(new GridLayout(1, 0, 0, 0));
    }

    private void setCorrectValues() {
        if (getHorizontalSizeText() > Constants.MAX_HORIZONTAL_SIZE) {
            horizontalSizeText.setText(String.valueOf(Constants.MAX_HORIZONTAL_SIZE));
        }
        else if(getHorizontalSizeText() <= 1) {
            horizontalSizeText.setText(String.valueOf(2));
        }

        if (getVerticalSizeTest() > Constants.MAX_VERTICAL_SIZE) {
            verticalSizeTest.setText(String.valueOf(Constants.MAX_VERTICAL_SIZE));
        }
        else if(getVerticalSizeTest() <= 1) {
            verticalSizeTest.setText(String.valueOf(2));
        }

        if (getBombCountText() > getHorizontalSizeText() * getVerticalSizeTest() - 1) {
            bombCountText.setText(String.valueOf(getHorizontalSizeText() * getVerticalSizeTest() - 1));
        }
        else if (getBombCountText() < 1) {
            bombCountText.setText(String.valueOf(1));
        }
    }
}
