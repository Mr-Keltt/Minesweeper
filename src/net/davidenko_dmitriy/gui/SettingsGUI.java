package net.davidenko_dmitriy.gui;

import net.davidenko_dmitriy.constants.Constants;

import javax.swing.*;
import java.awt.*;

public class SettingsGUI extends GUI {
    private JLabel horizontalSizeLabel;
    private JLabel verticalSizeLabel;
    private JLabel bombCountLabel;
    private JTextField horizontalSizeText;
    private JTextField verticalSizeTest;
    private JTextField bombCountText;
    private JButton applyButton;


    public SettingsGUI() {
        super("Settings Minesweeper");

        JPanel inputContainer = createInputContainer();
        JPanel buttonContainer = createButtonContainer();

        this.add(inputContainer);
        this.add(buttonContainer);
    }


    @Override
    protected void initWindow() {
        // Setting window parameters
        this.setSize(new Dimension(Constants.Settings_Window_Width, Constants.Settings_Window_Height));
        this.setLocation(Constants.Window_Location_X + 100, Constants.Window_Location_Y + 100);
        this.setResizable(false);
        this.setLayout(null);
        int margin = Constants.Settings_Margin;
        getRootPane().setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
    }

    private JPanel createInputContainer() {
        JPanel inputContainer = new JPanel();
        initInputContainer(inputContainer, 0, 0);

        horizontalSizeLabel = createLabel("Ширина");
        verticalSizeLabel = createLabel("Высота");
        bombCountLabel = createLabel("Количество бомб");
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

        applyButton = createButton("Применить изменения");

        buttonContainer.add(applyButton);

        return buttonContainer;
    }

    private void initInputContainer(JPanel inputContainer, int X, int Y) {
        int inputContainerWidth = this.getWidth() - frameWidth - Constants.Settings_Margin*2;
        int inputContainerHeight = (int)((this.getHeight() - frameHeight)*0.7) - Constants.Settings_Margin;

        inputContainer.setLayout(new GridLayout(3, 2, 10, 10));
        inputContainer.setLocation(X,Y);
        inputContainer.setSize(new Dimension(inputContainerWidth, inputContainerHeight));
    }

    private void initButtonContainer(JPanel buttonContainer, int X, int Y) {
        int buttonContainerWidth = this.getWidth() - frameWidth - Constants.Settings_Margin*2;
        int buttonContainerHeight =  (int)((this.getHeight() - frameHeight)*0.3) - Constants.Settings_Margin*2;

        buttonContainer.setSize(new Dimension(buttonContainerWidth, buttonContainerHeight));
        buttonContainer.setLocation(X, Y);
        buttonContainer.setLayout(new GridLayout(1, 0, 0, 0));
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(Constants.Settings_Text_Font);

        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(Constants.Settings_Text_Font);

        return textField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(Constants.Settings_Text_Font);

        return button;
    }
}
