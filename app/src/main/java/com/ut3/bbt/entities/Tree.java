package com.ut3.bbt.entities;

public class Tree extends Obstacle {
    public Tree(double x, double y) {
        super(x, y);
    }

    @Override
    public boolean isJumpable() {
        return false;
    }
}
