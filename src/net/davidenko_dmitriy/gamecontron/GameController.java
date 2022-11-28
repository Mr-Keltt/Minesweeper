package net.davidenko_dmitriy.gamecontron;

import net.davidenko_dmitriy.gameobjects.Field;
import net.davidenko_dmitriy.gui.GameGUI;
import net.davidenko_dmitriy.gui.SettingsGUI;
import net.davidenko_dmitriy.settings.Settings;

import java.awt.*;

public class GameController {
    private GameGUI gameGUI;
    private Field field;
    private int interactionType;


    public GameController() {
        field = new Field();
        gameGUI = new GameGUI(this, field);
        interactionType = 0;

        removingDisplayBug();
    }

    public void openSettings() {
        SettingsGUI settingsGUI = new SettingsGUI(this, gameGUI);
    }

    public void restartGame() {
        gameGUI.reset();
        field.reset();
        interactionType = 0;

        removingDisplayBug();
    }

    public void switchInteractionType() {
        if (interactionType == 0) {
            interactionType = 1;
            gameGUI.switchImageSwitchButton();
        }
        else {
            interactionType = 0;
            gameGUI.switchImageSwitchButton();
        }
    }

    public void applySettings(int horizontalSizeText, int verticalSizeTest, int bombCountText) {
        Settings.horizontalSize = horizontalSizeText;
        Settings.verticalSize = verticalSizeTest;
        Settings.bombCount = bombCountText;

        restartGame();
        removingDisplayBug();
    }

    public void startGame(int X, int Y) {
        field.startGame(X, Y);
        gameGUI.setActionListenerSwitchButton();
        gameGUI.getFieldGUI().startGame();

        gameGUI.getFieldGUI().getCellGUI(X, Y).open(1);
    }

    public void interactionWithCells(int X, int Y) {
        if (interactionType == 0) {
            gameGUI.getFieldGUI().getCellGUI(X, Y).open(0);
        }
        else {
            if (gameGUI.getFieldGUI().getCellGUI(X, Y).isMarked()) {
                gameGUI.getBombCounter().increase();
            }
            else {
                gameGUI.getBombCounter().reduce();
            }

            gameGUI.getFieldGUI().getCellGUI(X, Y).switchMarked();
            field.switchMarkedCell(X, Y);
        }
    }


    private void removingDisplayBug() {
        gameGUI.setState(Frame.ICONIFIED);
        gameGUI.setState(Frame.NORMAL);
    }
}
