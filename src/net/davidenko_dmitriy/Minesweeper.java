package net.davidenko_dmitriy;

import net.davidenko_dmitriy.gui.GameGUI;

import java.awt.*;


public class Minesweeper {
    public static void main(String[] args) {
        GameGUI gameGUI = new GameGUI();

        //removing the display bug
        gameGUI.setState(Frame.ICONIFIED);
        gameGUI.setState(Frame.NORMAL);
    }
}
