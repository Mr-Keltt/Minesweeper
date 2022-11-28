package net.davidenko_dmitriy.constants;

import java.awt.*;

public abstract class Constants {
    public static final int MIN_GAME_WINDOW_WIDTH = 500;
    public static final int WINDOW_LOCATION_X = 150;
    public static final int WINDOW_LOCATION_Y = 150;
    public static final int HEADER_HEIGHT = 70;
    public static final int SCORE_WIDTH = 130;
    public static final int HEADER_ELEMENT_HEIGHT = 50;
    public static final int CELL_EDGE_LENGTH = 25;
    public static final int START_HORIZONTAL_SIZE = 10;
    public static final int START_VERTICAL_SIZE = 10;
    public static final int START_BOMBS_COUNT = 10;
    public static final int MAX_HORIZONTAL_SIZE = 60;
    public static final int MAX_VERTICAL_SIZE = 30;
    public static final int SETTINGS_WINDOW_WIDTH = 400;
    public static final int SETTINGS_WINDOW_HEIGHT = 300;
    public static final int SETTINGS_MARGIN = 10;

    public static final String CELL_IMG_PATH = "src/net/davidenko_dmitriy/resources/img/cell.png";
    public static final String[] CELL_ENABLED_IMG_PATH = {"src/net/davidenko_dmitriy/resources/img/cell_enabled.png",
            "src/net/davidenko_dmitriy/resources/img/cell_enabled_1.png",
            "src/net/davidenko_dmitriy/resources/img/cell_enabled_2.png",
            "src/net/davidenko_dmitriy/resources/img/cell_enabled_3.png",
            "src/net/davidenko_dmitriy/resources/img/cell_enabled_4.png",
            "src/net/davidenko_dmitriy/resources/img/cell_enabled_5.png",
            "src/net/davidenko_dmitriy/resources/img/cell_enabled_6.png",
            "src/net/davidenko_dmitriy/resources/img/cell_enabled_7.png",
            "src/net/davidenko_dmitriy/resources/img/cell_enabled_8.png",
            "src/net/davidenko_dmitriy/resources/img/cell_enabled_9.png"};
    public static final String CELL_MARKED_IMG_PATH = "src/net/davidenko_dmitriy/resources/img/cell_mark.png";
    public static final String SETTING_BUTTON_IMG_PATH = "src/net/davidenko_dmitriy/resources/img/setting_button.png";
    public static final String RESTART_BUTTON_IMG_PATH = "src/net/davidenko_dmitriy/resources/img/restart_button.png";
    public static final String RESTART_BUTTON_LOSS_IMG_PATH = "src/net/davidenko_dmitriy/resources/img/restart_button_loss.png";
    public static final String RESTART_BUTTON_WIN_IMG_PATH = "src/net/davidenko_dmitriy/resources/img/restart_button_win.png";
    public static final String SWITCH_BUTTON_MARC_IMG_PATH = "src/net/davidenko_dmitriy/resources/img/switch_button_mark.png";
    public static final String SWITCH_BUTTON_SHOVEL_IMG_PATH = "src/net/davidenko_dmitriy/resources/img/switch_button_shovel.png";

    public static final Color HEADER_BACKGROUND = new  Color(200, 200, 200);
    public static final Color SCORE_BACKGROUND = new  Color(70,0,0);
    public static final Color SCORE_TEXT_COLOR = new  Color(255,0,0);
    public static final Color CELL_TEXT_COLOR = new  Color(0,150,0);

    public static final Font SCORE_TEXT_FONT = new Font("Arial", Font.BOLD, 30);
    public static final Font SETTINGS_TEXT_FONT = new Font("Arial", Font.BOLD, 20);
    public static final Font CELL_TEXT_FONT = new Font("Arial", Font.BOLD, 15);
}
