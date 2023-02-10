package com.ut3.bbt.entities;

import android.graphics.Color;

public class Tree extends Obstacle {
    public Tree(double x, double y) {
        super(x, y, 50, 150);

        paint.setColor(Color.GREEN);
    }

    @Override
    public boolean isJumpable() {
        return false;
    }
}
