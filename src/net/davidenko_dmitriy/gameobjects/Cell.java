package net.davidenko_dmitriy.gameobjects;

public class Cell {
    private int type;
    private boolean opened;
    private boolean marked;


    public Cell() {
        reset();
    }

    public void reset() {
        type = 0;
        opened = false;
        marked = false;
    }

    public void setBomb() {
        this.type = -10;
    }

    public void increaseType() {
        type++;
    }

    public int getType() {
        return type;
    }

    public void open() {
        if (!marked) {
            opened = true;
        }
    }

    public boolean getOpened() {
        return opened;
    }

    public void switchMarked() {
        if (!opened) {
            marked = !marked;
        }
    }

    public boolean getMarked() {
        return marked;
    }
}
