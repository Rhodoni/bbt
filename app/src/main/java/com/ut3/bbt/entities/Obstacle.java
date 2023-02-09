package com.ut3.bbt.entities;

public class Obstacle extends Entity implements Scrollable {

    private boolean jumpable;

    public Obstacle(boolean jumpable) {
        this.jumpable = jumpable;
    }

    @Override
    public void update() {

    }

    @Override
    public void scroll(int speed) {
        y = y + speed;
    }

    public boolean isJumpable() {
        return jumpable;
    }
}
