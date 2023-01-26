package com.ut3.bbt.entities;

public class Movable extends Entity{

    float maxSpeed;
    float speed;

    public Movable(float maxSpeed, float speed) {
        this.maxSpeed = maxSpeed;
        this.speed = speed;
    }

    public void collision(Entity entity) {

    }

    public void move() {
        x += speed;
    }

    public void turn() {

    }

    public void die() {

    }
}
