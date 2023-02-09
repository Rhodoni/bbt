package com.ut3.bbt.entities;

public abstract class Obstacle extends Entity implements Scrollable {

    public Obstacle(double x, double y) {
        super(x, y);
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
