package net.davidenko_dmitriy.gamecontron;

import net.davidenko_dmitriy.gameobjects.Cell;
import net.davidenko_dmitriy.gameobjects.Field;
import net.davidenko_dmitriy.settings.Settings;

import java.awt.*;

public class GameController {
    private Field field;

    public GameController () {
        field = new Field();
        field.startGame(5,5);
        field.print();
    }
}
