package com.ut3.bbt.entities;

public class Player extends Movable {
    public Player(float speed) {
        super(10, speed);
    }

    public void jump() {

    }

    public void scream() {

    }

    @Override
    public void collision(Entity entity) {
        die();
    }
}
