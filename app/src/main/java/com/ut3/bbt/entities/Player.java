package com.ut3.bbt.entities;

public class Player extends Movable {


    public Player(double x, double y, double maxSpeed, double speed) {
        super(x, y, maxSpeed);
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
