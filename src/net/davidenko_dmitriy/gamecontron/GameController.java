package net.davidenko_dmitriy.gamecontron;

import java.awt.*;

public class GameController {
    private int markedBombCount;
    private int markCount;
    private Cell[][] field;

    public GameController () {

    }

    public void createField(int horizontalSize, int verticalSize) {
        field = new Cell[horizontalSize][verticalSize];
    }

    public void zeroingField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = new Cell();
            }
        }
    }

    public void initCells(int bombCount) {
        Point[] bombsCord = new Point[bombCount];

        placeBombs(bombsCord);

        for (int i = 0; i < bombsCord.length; i++) {
            calculateNeighboringCells(bombsCord[i]);
        }
    }

    public void printField() {
        for (int i = 0; i < field.length; i++) {
            String s = "";

            for (int j = 0; j < field[i].length; j++) {
                s += String.valueOf(field[i][j].getType()) + "\t";
            }

            System.out.println(s);
        }
    }


    private void placeBombs(Point[] bombsCord) {
        int horizontalSize = field.length;
        int verticalSize = field[0].length;

        for (int i = 0; i < bombsCord.length; i++) {
            boolean notIs;
            Point cord;

            do {
                notIs = true;
                cord = new Point((int)(Math.random() * horizontalSize), (int)(Math.random() * verticalSize));

                for (int j = 0; j < i; j++) {
                    if (bombsCord[j].x == cord.x && bombsCord[j].y == cord.y) {
                        notIs = false;
                        break;
                    }
                }
            } while (!notIs);

            bombsCord[i] = cord;
            field[cord.x][cord.y].setBomb();
        }
    }

    private void calculateNeighboringCells(Point cord) {
        int X = cord.x;
        int Y = cord.y;
        int horiz = 0;
        int vert = 0;

        if (cord.x == 0) horiz = -1;
        if (cord.x == field.length - 1) horiz = 1;
        if (cord.y == 0) vert = -1;
        if (cord.y == field[0].length - 1) vert = 1;

        int beginI = (horiz != -1) ? X-1 : X;
        int endI = (horiz != 1) ? X+1 : X;
        int beginJ = (vert != -1) ? Y-1 : Y;
        int endJ = (vert != 1) ? Y+1 : Y;

        for (int i = beginI; i <= endI; i++) {
            for (int j = beginJ; j <= endJ; j++) {
                field[i][j].increaseType();
            }
        }
    }
}
