package com.ut3.bbt.entities;

public class Wall extends Obstacle {


    public Wall(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public boolean isJumpable() {
        return false;
    }
}
