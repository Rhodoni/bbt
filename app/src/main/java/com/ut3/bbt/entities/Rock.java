package com.ut3.bbt.entities;

public class Rock extends Obstacle {
    public Rock(double x, double y) {
        super(x, y);
    }

    @Override
    public boolean isJumpable() {
        return true;
    }
}
