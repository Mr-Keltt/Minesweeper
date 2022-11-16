package net.davidenko_dmitriy.constants;

import java.awt.*;
import java.io.InputStream;

public abstract class Constants {
    public static final int Min_Game_Window_Width = 500;
    public static final int Window_Location_X = 150;
    public static final int Window_Location_Y = 150;
    public static final int Header_Height = 70;
    public static final int Score_Width = 130;
    public static final int Score_Height = 50;
    public static final int Cell_Edge_Length = 40;
    public static final int Beginner_Horizontal_Size = 10;
    public static final int Beginner_Vertical_Size = 10;
    public static final int Beginner_Bombs_Count = 10;
    public static final int Amateur_Horizontal_Size = 16;
    public static final int Amateur_Vertical_Size = 16;
    public static final int Amateur_Bombs_Count = 40;
    public static final int Professional_Horizontal_Size = 30;
    public static final int Professional_Vertical_Size = 16;
    public static final int Professional_Bombs_Count = 99;
    public static final int Settings_Window_Width = 400;
    public static final int Settings_Window_Height = 300;

    public static final Color Header_Background = new  Color(200, 200, 200);
    public static final Color Score_Background = new  Color(70,0,0);
    public static final Color Score_Text_Color = new  Color(255,0,0);


    public static final Font Score_Text_Font = new Font("Arial", Font.BOLD, 30);
}
