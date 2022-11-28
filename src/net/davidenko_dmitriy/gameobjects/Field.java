package net.davidenko_dmitriy.gameobjects;

import net.davidenko_dmitriy.gamecontron.GameController;
import net.davidenko_dmitriy.gui.FieldGUI;
import net.davidenko_dmitriy.settings.Settings;

import java.awt.*;

public class Field {
    private GameController gameController;
    private int noMarkedBombCount;
    private int markCount;

    private Cell[][] container;
    private Point[] bombsCord;
    
    
    public Field(GameController gameController) {
        this.gameController = gameController;
        this.container = new Cell[Settings.horizontalSize][Settings.verticalSize];
        reset();
    }

    public Cell[][] getContainer() {
        return container;
    }

    public Cell getCell(int X, int Y) {
        return container[X][Y];
    }

    public int getMarkCount() {
        return markCount;
    }

    public int getNoMarkedBombCount() {
        return noMarkedBombCount;
    }

    // restarting the field
    public void reset() {
        container = new Cell[Settings.horizontalSize][Settings.verticalSize];

        // reset of all cells
        for (int i = 0; i < Settings.horizontalSize; i++) {
            for (int j = 0; j < Settings.verticalSize; j++) {
                container[i][j] = new Cell();
                container[i][j].reset();
            }
        }

        noMarkedBombCount = Settings.bombCount;
        markCount = 0;
    }

    // performs actions related to the field required to start the game
    public void start(int startX, int startY) {
        int bombCount = Settings.bombCount;
        bombsCord = new Point[bombCount];

        placeBombs(startX, startY);

        for (int i = 0; i < bombsCord.length; i++) {
            calculateNeighboringCells(bombsCord[i]);
        }
    }

    // opens all touching cells in which there are no bombs
    public void openCells(FieldGUI fieldGUI, int X, int Y) {
        Cell cell = getCell(X, Y);

        if (cell.getType() < 0) {
            for (int i = 0; i < bombsCord.length; i++) {
                getCell(bombsCord[i].x, bombsCord[i].y).open();
                fieldGUI.getCellGUI(bombsCord[i].x, bombsCord[i].y).open(cell.getType());
            }

            gameController.lossGame();
            return;
        }

        AreaImpact areaImpact = new AreaImpact(X, Y);

        if (cell.getType() == 0) {
            neatRecursiveOpeningCells(fieldGUI, X, Y);
        }
        else {
            boolean nothingAround = true;
            int countMarks = 0;

            for (int i = areaImpact.getLeftX(); i <= areaImpact.getRightX(); i++) {
                for (int j = areaImpact.getTopY(); j <= areaImpact.getBottomY(); j++) {
                    if (i == X && j == Y) {
                        continue;
                    }

                    if (getCell(i,j).getOpened()) {
                        nothingAround = false;
                    }
                }
            }

            if (nothingAround) {
                neatRecursiveOpeningCells(fieldGUI, X, Y);
            }
            else {
                sloppyRecursiveOpeningCells(fieldGUI, X, Y);
            }
        }
    }

    public void switchMarkedCell(int X, int Y) {
        Cell cell = container[X][Y];

        cell.switchMarked();

        if (cell.getMarked()) {
            markCount++;

            if (cell.getType() < 0) {
                noMarkedBombCount--;
            }
        }
        else {
            markCount--;

            if (cell.getType() < 0) {
                noMarkedBombCount++;
            }
        }
    }

    // todo temporary method for debugging
    public void print() {
        for (int i = 0; i < Settings.verticalSize; i++) {
            String s = "";

            for (int j = 0; j < Settings.horizontalSize; j++) {
                s += String.valueOf(container[j][i].getType()) + "\t";
            }

            System.out.println(s);
        }
    }

    public void sloppyRecursiveOpeningCells(FieldGUI fieldGUI, int X, int Y) {
        AreaImpact areaImpact = new AreaImpact(X, Y);

        if (countingNearestMarks(X, Y) >= getCell(X,Y).getType()) {
            for (int i = areaImpact.getLeftX(); i <= areaImpact.getRightX(); i++) {
                for (int j = areaImpact.getTopY(); j <= areaImpact.getBottomY(); j++) {
                    Cell curCell = getCell(i, j);

                    if (curCell.getMarked()) {
                        continue;
                    }
                    else if (curCell.getOpened()) {
                        continue;
                    }

                    curCell.open();
                    fieldGUI.getCellGUI(i, j).open(curCell.getType());
                    openCells(fieldGUI, i, j);
                }
            }
        }
    }


    // randomly place bombs across the field and stores their coordinates in a bombsCord array
    private void placeBombs(int startX, int startY) {
        int horizontalSize = container.length;
        int verticalSize = container[0].length;

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
            container[cord.x][cord.y].setBomb();
        }
    }

    // sets the correct type of cells located next to the bombs
    private void calculateNeighboringCells(Point cord) {
        int X = cord.x;
        int Y = cord.y;
        AreaImpact areaImpact = new AreaImpact(X, Y);

        for (int i = areaImpact.getLeftX(); i <= areaImpact.getRightX(); i++) {
            for (int j = areaImpact.getTopY(); j <= areaImpact.getBottomY(); j++) {
                container[i][j].increaseType();
            }
        }
    }

    private void neatRecursiveOpeningCells(FieldGUI fieldGUI, int X, int Y) {
        AreaImpact areaImpact = new AreaImpact(X, Y);

        for (int i = areaImpact.getLeftX(); i <= areaImpact.getRightX(); i++) {
            for (int j = areaImpact.getTopY(); j <= areaImpact.getBottomY(); j++) {
                Cell curCell = getCell(i, j);

                if (curCell.getMarked()) {
                    continue;
                }
                else if (curCell.getOpened()) {
                    continue;
                }
                else if (curCell.getType() < 0) {
                    continue;
                }

                curCell.open();
                fieldGUI.getCellGUI(i, j).open(curCell.getType());
                openCells(fieldGUI, i, j);
            }
        }
    }

    private int countingNearestMarks(int X, int Y) {
        AreaImpact areaImpact = new AreaImpact(X, Y);
        int countMarks = 0;

        for (int i = areaImpact.getLeftX(); i <= areaImpact.getRightX(); i++) {
            for (int j = areaImpact.getTopY(); j <= areaImpact.getBottomY(); j++) {
                if (i == X && j == Y) {
                    continue;
                }

                Cell curCell = getCell(i,j);

                if (curCell.getMarked()) {
                    countMarks++;
                }
            }
        }

        return countMarks;
    }
}
