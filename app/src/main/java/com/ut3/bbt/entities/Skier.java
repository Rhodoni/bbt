package com.ut3.bbt.entities;

public class Skier extends Movable implements Scrollable {

    public Skier(float speed) {
        super(10, 0);
    }

    @Override
    public void scroll(int speed) {
        y += speed * 0.8;
    }
}
