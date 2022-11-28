package net.davidenko_dmitriy.gui;

import net.davidenko_dmitriy.constants.Constants;
import net.davidenko_dmitriy.gamecontron.GameController;
import net.davidenko_dmitriy.gameobjects.Field;
import net.davidenko_dmitriy.settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FieldGUI {
    GameController gameController;
    GameGUI gameGUI;
    Field field;
    JPanel container;
    CellGUI[][] cellContainerGUI;


    public FieldGUI(GameController gameController, GameGUI gameGUI, Field field) {
        this.gameController = gameController;
        this.gameGUI = gameGUI;
        this.field = field;
        this.cellContainerGUI = new CellGUI[Settings.horizontalSize][Settings.verticalSize];
        container = new JPanel();
        reset();
    }

    public JPanel getContainer() {
        return container;
    }

    public CellGUI getCellGUI(int X, int Y) {
        return cellContainerGUI[X][Y];
    }

    public void reset() {
        gameGUI.remove(container);
        container = new JPanel();
        initField();

        cellContainerGUI = new CellGUI[Settings.horizontalSize][Settings.verticalSize];

        // reset of all cells
        for (int i = 0; i < Settings.horizontalSize; i++) {
            for (int j = 0; j < Settings.verticalSize; j++) {
                cellContainerGUI[i][j] = new CellGUI(gameController, gameGUI, container, i, j);

                container.add(getCellGUI(i, j).getCellButton());
            }
        }

        gameGUI.add(container);
    }

    public void startGame() {
        for (int i = 0; i < Settings.horizontalSize; i++) {
            for (int j = 0; j < Settings.verticalSize; j++) {
                getCellGUI(i, j).startGame();
            }
        }
    }


    private void initField() {
        container.setSize(Constants.CELL_EDGE_LENGTH*Settings.horizontalSize, Constants.CELL_EDGE_LENGTH*Settings.verticalSize);
        container.setLocation((gameGUI.getWidth()-gameGUI.getFrameWidth()- container.getWidth())/2, Constants.HEADER_HEIGHT);
        container.setLayout(new GridLayout(Settings.verticalSize, Settings.horizontalSize, 0, 0));
    }
}
