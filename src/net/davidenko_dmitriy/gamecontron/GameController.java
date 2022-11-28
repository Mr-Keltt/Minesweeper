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
        field = new Field(this);
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

    public void interactionWithCells(int X, int Y) {
        if (interactionType == 0) {
            if (field.getCell(X, Y).getMarked()) {
                return;
            }

            field.openCells(gameGUI.getFieldGUI(), X, Y);
        }
        else {
            if (field.getCell(X, Y).getOpened()) {
                field.sloppyRecursiveOpeningCells(gameGUI.getFieldGUI(), X, Y);
                return;
            }

            if (field.getCell(X, Y).getMarked()) {
                gameGUI.getBombCounter().increase();
            }
            else {
                gameGUI.getBombCounter().reduce();
            }

            gameGUI.getFieldGUI().getCellGUI(X, Y).switchMarked();
            field.switchMarkedCell(X, Y);

            if (field.getMarkCount() == Settings.bombCount && field.getNoMarkedBombCount() == 0) {
                winGame();
            }
        }
    }

    public void startGame(int X, int Y) {
        field.start(X, Y);
        field.openCells(gameGUI.getFieldGUI(), X, Y);

        gameGUI.setActionListenerSwitchButton();
        gameGUI.getFieldGUI().start();

        field.print();
    }

    public void lossGame() {
        gameGUI.loss();
    }

    public void winGame() {
        gameGUI.win();
    }

    private void removingDisplayBug() {
        gameGUI.setState(Frame.ICONIFIED);
        gameGUI.setState(Frame.NORMAL);
    }
}
