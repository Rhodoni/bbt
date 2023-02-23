package com.ut3.bbt.game;

import com.ut3.bbt.entities.Entity;

public class Utils {
    public static boolean isTouching(double x1, double y1, double w1, double h1, double x2, double y2, double w2, double h2) {
        return  x1 < x2 + w2 &&
                x1 + w1 > x2 &&
                y1 < y2 + h2 &&
                h1 + y1 > y2;
    }

    public static boolean isTouching(Entity e1, Entity e2) {
        return isTouching(e1.getX(), e1.getY(), e1.getWidth(), e1.getHeight(), e2.getX(), e2.getY(), e2.getWidth(), e2.getHeight());
    }

    public static boolean isInScreen(double x, double y, double w, double h, double screenWidth, double screenHeight) {
        return isTouching(x, y, w, h, 0, 0, screenWidth, screenHeight);
    }

    public static boolean isInScreen(Entity e, double screenWidth, double screenHeight) {
        return isInScreen(e.getX(), e.getY(), e.getWidth(), e.getHeight(), screenWidth, screenHeight);
    }

    public static boolean isInGame(Entity e, double screenWidth, double screenHeight) {
        return isTouching(e.getX(), e.getY(), e.getWidth(), e.getHeight(), -screenWidth, 0, screenWidth*2, screenHeight);
    }
}
