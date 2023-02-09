package com.ut3.bbt.entities;

public abstract class Movable extends Entity {
    double maxSpeed;
    double speed;
    double acceleration;

    public CollideBox hurtBox;

    public Movable(double x, double y, double maxSpeed) {
        super(x, y);

        this.maxSpeed = maxSpeed;
        this.speed = maxSpeed / 2;
        this.acceleration = 0.1;

        this.hurtBox = new CollideBox(1, 2);
    }

    public boolean checkCollision(Entity entity) {
        return x < entity.x + entity.hitBox.width &&
                x + hurtBox.width > entity.x &&
                y < entity.y + entity.hitBox.height &&
                hurtBox.height + y > entity.y;
    }

    public abstract void collision(Entity entity);

    @Override
    public void update() {
        updateSpeed();
        updatePosition();
    }

    public void updateSpeed() {
        speed = Math.min(maxSpeed, speed + acceleration);
    }

    public void updatePosition() {
        x += speed;
    }

    public void turn() {
        acceleration = -acceleration;
    }

    public void die() {

    }
}
