package com.ut3.bbt.entities;

public abstract class Movable extends Entity {
    double maxSpeed;
    double speed;
    double acceleration;

    public CollideBox hurtBox;

    public Movable(double x, double y, double maxSpeed) {
        super(x, y, 50, 100);

        this.maxSpeed = maxSpeed;
        this.speed = 0;
        this.acceleration = 0.03;

        this.hurtBox = new CollideBox(0, 0, width, height);
    }

    public void checkCollision(Entity entity) {
        if(x < entity.x + entity.hitBox.width &&
                x + hurtBox.width > entity.x &&
                y < entity.y + entity.hitBox.height &&
                hurtBox.height + y > entity.y) {
            collision(entity);
        }
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
