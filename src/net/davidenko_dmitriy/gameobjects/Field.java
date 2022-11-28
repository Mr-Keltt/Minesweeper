package net.davidenko_dmitriy.gameobjects;

import net.davidenko_dmitriy.gui.CellGUI;
import net.davidenko_dmitriy.settings.Settings;

import java.awt.*;

public class Field {
    private int markedBombCount;
    private int markCount;

    private Cell[][] cellContainer;
    private Point[] bombsCord;
    
    
    public Field() {
        cellContainer = new Cell[Settings.horizontalSize][Settings.verticalSize];
        reset();
    }

    public Cell[][] getCellContainer() {
        return cellContainer;
    }

    public int getMarkCount() {
        return markCount;
    }

    public int getMarkedBombCount() {
        return markedBombCount;
    }

    // restarting the field
    public void reset() {
        // recreating the cellContainer if the field size has changed
        if (getCellContainer().length != Settings.horizontalSize || getCellContainer()[0].length != Settings.verticalSize) {
            cellContainer = new Cell[Settings.horizontalSize][Settings.verticalSize];
        }

        // reset of all cells
        for (int i = 0; i < Settings.horizontalSize; i++) {
            for (int j = 0; j < Settings.verticalSize; j++) {
                cellContainer[i][j] = new Cell();
                cellContainer[i][j].reset();
            }
        }

        markedBombCount = 0;
        markCount = 0;
    }

    // performs actions related to the field required to start the game
    public void startGame(int startX, int startY) {
        int bombCount = Settings.bombCount;
        bombsCord = new Point[bombCount];

        placeBombs(startX, startY);

        for (int i = 0; i < bombsCord.length; i++) {
            calculateNeighboringCells(bombsCord[i]);
        }
    }

    // opens all touching cells in which there are no bombs
    public void openCells(CellGUI[][] cellContainerGUI, int X, int Y) {

    }

    public void switchMarkedCell(int X, int Y) {
        Cell cell = cellContainer[X][Y];

        cell.switchMarked();

        if (cell.getMarked()) {
            markCount++;

            if (cell.getType() < 0) {
                markedBombCount++;
            }
        }
        else {
            markCount--;

            if (cell.getType() < 0) {
                markedBombCount--;
            }
        }
    }

    // todo temporary method for debugging
    public void print() {
        for (int i = 0; i < cellContainer.length; i++) {
            String s = "";

            for (int j = 0; j < cellContainer[i].length; j++) {
                s += String.valueOf(cellContainer[i][j].getType()) + "\t";
            }

            System.out.println(s);
        }
    }



    // randomly place bombs across the field and stores their coordinates in a bombsCord array
    private void placeBombs(int startX, int startY) {
        int horizontalSize = cellContainer.length;
        int verticalSize = cellContainer[0].length;

        for (int i = 0; i < bombsCord.length; i++) {
            boolean notIs;
            Point cord;

            do {
                notIs = true;
                cord = new Point((int)(Math.random() * horizontalSize), (int)(Math.random() * verticalSize));

                if (startX == cord.x && startY == cord.y)
                {
                    notIs = false;
                }
                else {
                    for (int j = 0; j < i; j++) {
                        if (bombsCord[j].x == cord.x && bombsCord[j].y == cord.y) {
                            notIs = false;
                            break;
                        }
                    }
                }
            } while (!notIs);

            bombsCord[i] = cord;
            cellContainer[cord.x][cord.y].setBomb();
        }
    }

    // sets the correct type of cells located next to the bombs
    private void calculateNeighboringCells(Point cord) {
        int X = cord.x;
        int Y = cord.y;
        AreaImpact areaImpact = new AreaImpact(X, Y, Settings.horizontalSize, Settings.verticalSize);

        for (int i = areaImpact.getLeftX(); i <= areaImpact.getRightX(); i++) {
            for (int j = areaImpact.getTopY(); j <= areaImpact.getBottomY(); j++) {
                cellContainer[i][j].increaseType();
            }
        }
    }
}
