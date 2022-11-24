package net.davidenko_dmitriy.gamecontron;

public class Cell {
    private int type;
    private boolean opened;
    private boolean marked;


    public Cell() {
        reset();
    }

    public void setType(int type) {
        if (type >= -1 && type <= 8)
        {
            this.type = type;
        }
    }

    public int getType() {
        return type;
    }

    public void switchOpened() {
        opened = !opened;
    }

    public boolean getOpened() {
        return opened;
    }

    public void switchMarked() {
        marked = !marked;
    }

    public boolean getMarked() {
        return marked;
    }

    public void reset() {
        type = 0;
        opened = false;
        marked = false;
    }
}
