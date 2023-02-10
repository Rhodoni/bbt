package com.ut3.bbt.entities;

public abstract class Obstacle extends Entity implements Scrollable {

    public Obstacle(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void update() {

    }

    @Override
    public void scroll(int speed) {
        y -= speed;
    }

    public abstract boolean isJumpable();
}
