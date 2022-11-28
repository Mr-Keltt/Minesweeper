package net.davidenko_dmitriy.gui;

import net.davidenko_dmitriy.constants.Constants;
import net.davidenko_dmitriy.gamecontron.GameController;
import net.davidenko_dmitriy.settings.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CellGUI {
    private GameController gameController;
    private GameGUI gameGUI;
    private JPanel container;
    private JButton cellButton;
    private ActionListener startActionListener;
    private int X;
    private int Y;
    private boolean marked;


    public CellGUI(GameController gameController, GameGUI gameGUI, JPanel container, int X, int Y) {
        this.gameController = gameController;
        this.gameGUI = gameGUI;
        this.container = container;
        this.X = X;
        this.Y = Y;

        cellButton = gameGUI.createButton(Constants.CELL_IMG_PATH, Constants.CELL_IMG_PATH);
        startActionListener = createActionListener();
        init();
    }

    public JButton getCellButton() {
        return cellButton;
    }

    public boolean getMarked() {
        return marked;
    }

    public void open(int type) {
        try {
            String path;

            if (type < 0) {
                path = Constants.CELL_ENABLED_IMG_PATH[9];
            }
            else {
                if (type == 0) {
                    cellButton.setEnabled(false);
                }

                path = Constants.CELL_ENABLED_IMG_PATH[type];
            }

            Image icon = ImageIO.read(new File(path));
            cellButton.setIcon(new ImageIcon(icon));

            Image disabledIcon = ImageIO.read(new File(path));
            cellButton.setDisabledIcon(new ImageIcon(disabledIcon));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchMarked() {
//        container.remove(cellButton);
        marked = !marked;

        try {
            if (marked) {
                Image icon = ImageIO.read(new File(Constants.CELL_MARKED_IMG_PATH));
                cellButton.setIcon(new ImageIcon(icon));

                Image disabledIcon = ImageIO.read(new File(Constants.CELL_MARKED_IMG_PATH));
                cellButton.setDisabledIcon(new ImageIcon(disabledIcon));
            }
            else {
                Image icon = ImageIO.read(new File(Constants.CELL_IMG_PATH));
                cellButton.setIcon(new ImageIcon(icon));

                Image disabledIcon = ImageIO.read(new File(Constants.CELL_IMG_PATH));
                cellButton.setDisabledIcon(new ImageIcon(disabledIcon));
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

//        container.add(cellButton, X, Y);
    }

    public void startGame() {
        cellButton.removeActionListener(startActionListener);
        cellButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.interactionWithCells(X, Y);
            }
        });
    }


    private void init() {
        cellButton.setSize(new Dimension(Constants.CELL_EDGE_LENGTH-2, Constants.CELL_EDGE_LENGTH-2));
        cellButton.setForeground(Constants.CELL_TEXT_COLOR);
        cellButton.setFont(Constants.CELL_TEXT_FONT);

        cellButton.addActionListener(startActionListener);
    }

    private ActionListener createActionListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.startGame(X, Y);
            }
        };
    }
}
