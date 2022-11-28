package net.davidenko_dmitriy.gameobjects;

import net.davidenko_dmitriy.settings.Settings;

public class AreaImpact {
    private int leftX;
    private int rightX;
    private int topY;
    private int bottomY;


    public AreaImpact(int centerX, int centerY) {
        int horiz = 0;
        int vert = 0;

        // location verification
        if (centerX == 0) horiz = -1;
        if (centerX == Settings.horizontalSize - 1) horiz = 1;
        if (centerY == 0) vert = -1;
        if (centerY == Settings.verticalSize - 1) vert = 1;

        // setting up the impact area
        leftX = (horiz != -1) ? centerX - 1 : centerX;
        rightX = (horiz != 1) ? centerX + 1 : centerX;
        topY = (vert != -1) ? centerY - 1 : centerY;
        bottomY = (vert != 1) ? centerY + 1 : centerY;
    }

    public int getLeftX() {
        return leftX;
    }

    public int getRightX() {
        return rightX;
    }

    public int getTopY() {
        return topY;
    }

    public int getBottomY() {
        return bottomY;
    }
}
