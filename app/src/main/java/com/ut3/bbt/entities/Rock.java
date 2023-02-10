package com.ut3.bbt.entities;

import android.graphics.Color;

public class Rock extends Obstacle {
    public Rock(double x, double y) {
        super(x, y, 50, 50);

        paint.setColor(Color.GRAY);
    }

    @Override
    public boolean isJumpable() {
        return true;
    }
}
