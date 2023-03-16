package com.ut3.bbt.entities;

import android.graphics.Canvas;
import android.os.Handler;

import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class Movable extends Entity {
    double maxSpeed;
    double speed;
    double acceleration;
    boolean turning;

    public CollideBox hurtBox;

    public Movable(double x, double y, double width, double height, double maxSpeed) {
        super(x, y, width, height);

        this.maxSpeed = maxSpeed;
        this.speed = 0;
        this.acceleration = 0.1;
        this.turning = false;

        this.hurtBox = new CollideBox(0, 0, width, height);
    }


    public void checkCollision(Entity entity) {
        if (x + hurtBox.x < entity.x + entity.hitBox.x + entity.hitBox.width &&
            x + hurtBox.x + hurtBox.width > entity.x + entity.hitBox.x &&
            y + hurtBox.y < entity.y + entity.hitBox.y + entity.hitBox.height &&
            y + hurtBox.y + hurtBox.height > entity.y + entity.hitBox.y)
        {
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
        double newSpeed = speed + acceleration;
        speed = Math.max(Math.min(maxSpeed, newSpeed), -maxSpeed);
    }

    public void updatePosition() {
        x += speed;
    }

    public void turn() {
        if (!isTurning()) {
            acceleration = -acceleration;
            turning = true;

            Executors.newSingleThreadScheduledExecutor()
                    .schedule(() -> this.turning = false,4000, TimeUnit.MILLISECONDS);
        }
    }

    public boolean isTurning() {
        return turning;
    }

    public void die() {
        System.out.println("MORT");
    }

    public double getAcceleration() {
        return this.acceleration;
    }
    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }
}
